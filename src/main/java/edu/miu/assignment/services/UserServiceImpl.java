package edu.miu.assignment.services;

import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.models.dtos.UserCreateDto;
import edu.miu.assignment.models.dtos.UserDto;
import edu.miu.assignment.repositories.CommentRepository;
import edu.miu.assignment.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CommentRepository commentRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> findAll() {
        return this.mapper.map(this.userRepository.findAll(), new TypeToken<List<UserDto>>() {}.getType());
    }

    @Override
    public UserDto findById(long id) {
        return this.mapper.map(this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found")), UserDto.class);
    }

    @Override
    public UserDto save(UserCreateDto post) {
        return this.mapper.map(this.userRepository.save(this.mapper.map(post, User.class)), UserDto.class);
    }

    @Override
    public void delete(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDto update(long id, UserCreateDto post) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not exist"));
        existingUser.setName(post.getName());
        return this.mapper.map(this.userRepository.save(existingUser), UserDto.class);
    }

    @Override
    public List<PostDto> findAllPostsByUserId(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return this.mapper.map(user.getPosts(), new TypeToken<List<PostDto>>() {}.getType());
    }

    @Override
    public List<UserDto> findAllUsersHavingPostGreaterThan(int size) {
        return this.mapper.map(this.userRepository.findAllUsersHavingPostGreaterThan(size), new TypeToken<List<UserDto>>() {}.getType());
    }

    @Override
    public CommentDto getCommentsByUserIdAndPostId(long userId, long postId, long commentId) {
        return this.mapper.map(this.commentRepository.findByUserIdPostIdAndCommentId(userId, postId, commentId).orElseThrow(() -> new RuntimeException("Not found")), CommentDto.class);
    }

}
