package com.example.telegraph.service;

import com.example.telegraph.dto.PostDto;
import com.example.telegraph.entity.PostEntity;
import com.example.telegraph.entity.UserEntity;
import com.example.telegraph.exception.MyCustomException;
import com.example.telegraph.repository.PostRepository;
import com.example.telegraph.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public PostEntity add(PostDto postDto, UUID id){
        if (postDto.getName().isBlank() && postDto.getContent().isBlank()){
            throw new MyCustomException("post name or content is Blank");
        }
        PostEntity map = modelMapper.map(postDto, PostEntity.class);
        UserEntity user = userRepository.findById(id).get();
        map.setAuthor(user);
        map.setUrl(map.getName()+"-"+ LocalDateTime.now());
        return postRepository.save(map);
    }

    public List<PostEntity>searchUserPostsById(String name,String title){
        return postRepository.findPostEntitiesByNameOrTitle(name, title, Sort.by(Sort.Order.asc("createdDate")));
    }
    public List<PostEntity>getUserPost(UUID id){
        return postRepository.findPostEntitiesByAuthor_Id(id,Sort.by(Sort.Order.asc("name")));
    }
    public List<PostEntity> updateAndShow(String url){
         return postRepository.findPostEntitiesByUrl(url);
    }

}
