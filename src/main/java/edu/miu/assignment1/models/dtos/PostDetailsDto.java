package edu.miu.assignment1.models.dtos;

import lombok.Data;

@Data
public class PostDetailsDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private UserDto user;
}
