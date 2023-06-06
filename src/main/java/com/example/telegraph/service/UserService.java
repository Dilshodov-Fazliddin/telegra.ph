package com.example.telegraph.service;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.exception.MyCustomException;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public UserEntity add(UserDto userDto) {
        if (userDto.getName().isBlank() || userDto.getUsername().isBlank() || userDto.getPassword().isBlank()) {
            throw new MyCustomException("User properties is blank");
        }
        try {
            UserEntity user = modelMapper.map(userDto, UserEntity.class);

            return userRepository.save(user);
        } catch (Exception e) {
            throw new MyCustomException("Username already exist");
        }
    }


    public UserEntity signIn(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            throw new MyCustomException("user name or password is blank");
        }
        return userRepository.getByUsernameAndPassword(username, password);
    }


}
