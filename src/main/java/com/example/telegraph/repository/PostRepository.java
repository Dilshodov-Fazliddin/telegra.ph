package com.example.telegraph.repository;

import com.example.telegraph.entity.PostEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity>findPostEntitiesByNameOrTitle(String name, String description, Sort sort);


    List<PostEntity>findPostEntitiesByAuthor_Id(UUID id,Sort sort);

    List<PostEntity>findPostEntitiesByUrl(String url);
}
