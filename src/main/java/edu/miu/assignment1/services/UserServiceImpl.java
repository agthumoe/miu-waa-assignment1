package edu.miu.assignment1.services;

import edu.miu.assignment1.helpers.ListMapper;
import edu.miu.assignment1.models.User;
import edu.miu.assignment1.models.dtos.PostDto;
import edu.miu.assignment1.models.dtos.UserCreateDto;
import edu.miu.assignment1.models.dtos.UserDto;
import edu.miu.assignment1.repositories.PostRepository;
import edu.miu.assignment1.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final ListMapper listMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository, ModelMapper mapper, ListMapper listMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.listMapper = listMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return this.listMapper.mapList(this.userRepository.findAll(), UserDto.class);
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
        return this.listMapper.mapList(user.getPosts(), PostDto.class);
    }

    @Override
    public List<UserDto> findAllUsersHavingPostGreaterThan(int size) {
        return this.listMapper.mapList(this.userRepository.findAllUsersHavingPostGreaterThan(size), UserDto.class);
    }
}
