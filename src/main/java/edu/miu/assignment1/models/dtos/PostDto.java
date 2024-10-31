package edu.miu.assignment1.models.dtos;

import lombok.*;

@Data
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
}
