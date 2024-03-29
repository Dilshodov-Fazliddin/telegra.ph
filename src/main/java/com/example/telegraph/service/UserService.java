package com.example.telegraph.service;

import com.example.telegraph.dto.LoginDto;
import com.example.telegraph.dto.UserDto;
import com.example.telegraph.dto.response.JwtResponse;
import com.example.telegraph.entity.enums.Role;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.entity.enums.UserStatus;
import com.example.telegraph.exception.AuthFailedException;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.exception.RequestValidationException;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserEntity saveUser(UserDto userDto, List<Role> roles, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setUserRoles(roles);
        userEntity.setHasBlocked(UserStatus.ACTIVE);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    public JwtResponse signIn(LoginDto loginDto){

        UserEntity user=userRepository.findUserEntityByUsername(loginDto.getUsername())
                .orElseThrow(
                () -> new DataNotFoundException("User not found")
        );
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                String accessToken = jwtService.generateAccessToken(user);
                return JwtResponse.builder().accessToken(accessToken).build();
            }
        throw new AuthFailedException("User Not found or user is blocked");
    }


    public Boolean blockUser(UUID userId){
        UserEntity user =userRepository.findById(userId)
                .orElseThrow(()->new DataNotFoundException("user not found"));
        user.setHasBlocked(UserStatus.BLOCKED);
        userRepository.save(user);
        return true;
    }

    public Boolean unblockUser(UUID userId){
        UserEntity user =userRepository.findById(userId).
                orElseThrow(()->new DataNotFoundException("user not found"));
        user.setHasBlocked(UserStatus.ACTIVE);
        userRepository.save(user);
        return true;
    }

}
