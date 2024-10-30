package edu.miu.assignment1.services;

import edu.miu.assignment1.models.Post;
import edu.miu.assignment1.models.User;
import edu.miu.assignment1.models.dtos.PostDto;
import edu.miu.assignment1.models.dtos.UserCreateDto;
import edu.miu.assignment1.models.dtos.UserDto;
import edu.miu.assignment1.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    private List<UserDto> map(List<User> posts) {
        return posts.stream().map(post -> mapper.map(post, UserDto.class)).collect(Collectors.toList());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> findAll() {
        return this.map(this.userRepository.findAll());
    }

    @Override
    public UserDto findById(long id) {
        return this.mapper.map(this.userRepository.findById(id), UserDto.class);
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
}
