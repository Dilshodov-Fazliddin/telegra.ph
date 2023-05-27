package com.example.telegraph.service;

import com.example.telegraph.dto.UserDto;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public int add(UserDto userDto){
        System.out.println(userDto);
        try {
            UserEntity user = modelMapper.map(userDto, UserEntity.class);
            UserEntity save = userRepository.save(user);
            System.out.println(save);
        }catch (DataIntegrityViolationException e){
            return 500;
        }
        return 200;
    }


    public UserEntity signIn(String username, String password){
        return userRepository.getByUsernameAndPassword(username, password);
    }
}
