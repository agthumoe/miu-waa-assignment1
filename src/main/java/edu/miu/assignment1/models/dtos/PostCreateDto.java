package edu.miu.assignment1.models.dtos;

import lombok.*;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private String author;
    private long userId;
}
