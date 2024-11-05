package edu.miu.assignment.services.impl;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.models.Role;
import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.models.dtos.UserCreateDto;
import edu.miu.assignment.models.dtos.UserDto;
import edu.miu.assignment.others.CustomMapper;
import edu.miu.assignment.repositories.CommentRepository;
import edu.miu.assignment.repositories.PostRepository;
import edu.miu.assignment.repositories.RoleRepository;
import edu.miu.assignment.repositories.UserRepository;
import edu.miu.assignment.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CustomMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDto> findAll() {
        return this.mapper.map(this.userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findById(long id) {
        return this.mapper.map(this.userRepository.findById(id).orElseThrow(() -> new HttpStatusException("User not found", HttpStatus.NOT_FOUND)), UserDto.class);
    }

    @Override
    public UserDto save(UserCreateDto dto) {
        User user = this.mapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(this.mapRoles(dto.getRoles()));
        return this.mapper.map(this.userRepository.save(user), UserDto.class);
    }

    @Override
    public void delete(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto update(long id, UserCreateDto dto) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new HttpStatusException("User not found", HttpStatus.NOT_FOUND));
        existingUser.setName(dto.getName());
        existingUser.setRoles(this.mapRoles(dto.getRoles()));
        return this.mapper.map(this.userRepository.save(existingUser), UserDto.class);
    }

    @Override
    public List<PostDto> findAllPostsByUserId(long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new HttpStatusException("User not found", HttpStatus.NOT_FOUND));
        return this.mapper.map(user.getPosts(), PostDto.class);
    }

    @Override
    public List<UserDto> findAllUsersHavingPostGreaterThan(int size) {
        return this.mapper.map(this.userRepository.findAllUsersHavingPostGreaterThan(size), UserDto.class);
    }

    @Override
    public CommentDto getCommentsByUserIdAndPostId(long userId, long postId, long commentId) {
        return this.mapper.map(this.commentRepository.findByUserIdPostIdAndCommentId(userId, postId, commentId).orElseThrow(() -> new HttpStatusException("Not Found", HttpStatus.NOT_FOUND)), CommentDto.class);
    }

    @Override
    public List<UserDto> findAllUsersThatMadePostsWithinGivenTitle(String title) {
        return this.mapper.map(this.userRepository.findAllUsersThatMadePostsWithinGivenTitle(title), UserDto.class);
    }

    @Override
    public PostDto findByUserIdAndPostId(long userId, long postId) {
        return this.mapper.map(this.postRepository.findByUserIdAndPostId(userId, postId).orElseThrow(() -> new HttpStatusException("Not Found", HttpStatus.NOT_FOUND)), PostDto.class);
    }

    @Override
    public List<CommentDto> findAllCommentsByUserIdAndPostId(long userId, long postId) {
        return this.mapper.map(this.commentRepository.findAllCommentsByUserIdAndPostId(userId, postId), CommentDto.class);
    }

    private List<Role> mapRoles(List<String> roles) {
        return roles.stream()
                .map(role -> this.roleRepository.findByNameIgnoreCase(role).orElseThrow(() -> new HttpStatusException("Role: " + role + " not found", HttpStatus.NOT_FOUND)))
                .collect(Collectors.toList());
    }
}
