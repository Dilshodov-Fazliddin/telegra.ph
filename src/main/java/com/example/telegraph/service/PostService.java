package com.example.telegraph.service;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.exception.DataNotFoundException;
import com.example.telegraph.exception.RequestValidationException;
import com.example.telegraph.repository.PostRepository;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public PostEntity add(PostDto postDto, UUID id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }

            PostEntity map = modelMapper.map(postDto, PostEntity.class);
            UserEntity user = userRepository.findById(id).get();
            map.setAuthor(user);
            map.setUrl(map.getName() + "-" + LocalDateTime.now());
            return postRepository.save(map);

    }

    public List<PostEntity>searchUserPostsById(String name,String title,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
        return postRepository.findPostEntitiesByNameOrTitle(name, title, Sort.by(Sort.Order.asc("createdDate")));
    }
    public List<PostEntity>getUserPost(UUID id,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
            return postRepository.findPostEntitiesByAuthor_Id(id, Sort.by(Sort.Order.asc("name")));
    }
    public List<PostEntity> updateAndShow(String url,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw  new RequestValidationException(errors);
        }
            return postRepository.findPostEntitiesByUrl(url);
    }

    public void deletePostById(UUID id){
       userRepository.findById(id).orElseThrow(()->new DataNotFoundException("post entity not found please try again"));
    }
}
