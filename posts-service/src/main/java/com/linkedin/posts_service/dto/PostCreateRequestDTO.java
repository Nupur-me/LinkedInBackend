package com.linkedin.posts_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostCreateRequestDTO {

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 100, message = "Content cannot exceed 100 characters")
    private String content;

}
