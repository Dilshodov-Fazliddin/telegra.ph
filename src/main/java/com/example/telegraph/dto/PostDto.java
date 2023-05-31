package com.example.telegraph.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PostDto {
    @NotBlank(message ="name cannot be blank")
    private String name;
    @NotBlank(message ="title cannot be blank")
    private String title;
    @NotBlank(message ="content cannot be blank")
    private String content;

}
