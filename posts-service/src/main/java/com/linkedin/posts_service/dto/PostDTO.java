package com.linkedin.posts_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    private Long userId;
    private LocalDateTime createdAt;
}
