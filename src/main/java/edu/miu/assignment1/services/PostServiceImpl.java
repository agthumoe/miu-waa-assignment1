package edu.miu.assignment1.services;

import edu.miu.assignment1.models.Post;
import edu.miu.assignment1.models.PostCreateDto;
import edu.miu.assignment1.models.PostDto;
import edu.miu.assignment1.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper postMapper;

    private List<PostDto> map(List<Post> posts) {
        return posts.stream().map(post -> postMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper postMapper) {
        this.postRepository = postRepository;
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
    public PostDto findById(long id) {
        return this.postMapper.map(this.postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found")), PostDto.class);
    }

    @Override
    public PostDto save(PostCreateDto post) {
        return this.postMapper.map(this.postRepository.save(this.postMapper.map(post, Post.class)), PostDto.class);
    }

    @Override
    public void delete(long id) {
        this.postRepository.delete(id);
    }

    @Override
    public PostDto update(long id, PostCreateDto post) {
        return this.postMapper.map(this.postRepository.update(id, this.postMapper.map(post, Post.class)), PostDto.class);
    }
}
