package edu.miu.assignment.services;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.models.Post;
import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.PostCreateDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.repositories.PostRepository;
import edu.miu.assignment.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper postMapper;

    private List<PostDto> map(List<Post> posts) {
        return posts.stream().map(post -> postMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ModelMapper postMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDto> findAll() {
        return this.map(this.postRepository.findAll());
    }

    @Override
    public List<PostDto> findByAuthor(String author) {
        return this.map(this.postRepository.findByAuthor(author));
    }

    @Override
    public List<PostDto> findByAuthorContaining(String author) {
        return this.map(this.postRepository.findByAuthorContaining(author));
    }

    @Override
    public List<PostDto> findByTitle(String title) {
        return this.postMapper.map(this.postRepository.findByTitle(title), new TypeToken<List<PostDto>>() {}.getType());
    }

    @Override
    public PostDto findById(long id) {
        return this.postMapper.map(this.postRepository.findById(id).orElseThrow(() -> new HttpStatusException("Post not found", HttpStatus.NOT_FOUND)), PostDto.class);
    }

    @Override
    @Transactional
    public PostDto create(long userId, PostCreateDto dto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new HttpStatusException("User not found", HttpStatus.NOT_FOUND));
        user.getPosts().add(this.postMapper.map(dto, Post.class));
        return this.postMapper.map(dto, PostDto.class);
    }

    @Override
    public void delete(long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    public PostDto update(long id, PostCreateDto post) {
        Post existingPost = this.postRepository.findById(id).orElseThrow(() -> new HttpStatusException("Post not found", HttpStatus.NOT_FOUND));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        this.postRepository.save(existingPost);
        return this.postMapper.map(existingPost, PostDto.class);
    }
}
