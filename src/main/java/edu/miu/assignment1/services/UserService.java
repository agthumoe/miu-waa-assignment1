package edu.miu.assignment1.services;

import edu.miu.assignment1.models.dtos.*;

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
