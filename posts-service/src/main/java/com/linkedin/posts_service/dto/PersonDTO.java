package com.linkedin.posts_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonDTO {

    private Long id;

    private Long userId;

    @NotBlank(message = "Name is required")
    private String name;
}