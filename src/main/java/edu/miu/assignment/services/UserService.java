package edu.miu.assignment.services;

import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.models.dtos.UserCreateDto;
import edu.miu.assignment.models.dtos.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long id);
    UserDto save(UserCreateDto post);
    void delete(long id);
    UserDto update(long id, UserCreateDto post);
    List<PostDto> findAllPostsByUserId(long id);
    List<UserDto> findAllUsersHavingPostGreaterThan(int size);
    CommentDto getCommentsByUserIdAndPostId(long userId, long postId, long commentId);
}
