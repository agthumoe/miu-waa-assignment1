package edu.miu.assignment.models.dtos;

import lombok.Data;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
}
