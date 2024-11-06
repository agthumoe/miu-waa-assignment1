package edu.miu.assignment.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostCreateDto {
    @NotBlank
    private String title;
    private String content;
    private String author;
}
