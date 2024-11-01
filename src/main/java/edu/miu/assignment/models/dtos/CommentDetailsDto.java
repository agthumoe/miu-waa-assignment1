package edu.miu.assignment.models.dtos;

import lombok.Data;

@Data
public class CommentDetailsDto {
    private long id;
    private String name;
    private PostDetailsDto post;
}
