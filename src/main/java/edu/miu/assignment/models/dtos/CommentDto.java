package edu.miu.assignment.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String name;
}
