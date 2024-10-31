package edu.miu.assignment1.services;

import edu.miu.assignment1.models.Comment;
import edu.miu.assignment1.models.Post;
import edu.miu.assignment1.models.dtos.CommentDto;
import edu.miu.assignment1.repositories.CommentRepository;
import edu.miu.assignment1.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper mapper;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper mapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> findAll() {
        return this.mapper.map(commentRepository.findAll(), new TypeToken<List<CommentDto>>() {}.getType());
    }

    @Override
    public CommentDto findById(long id) {
        return this.mapper.map(this.commentRepository.findById(id), CommentDto.class);
    }

    @Override
    @Transactional
    public CommentDto save(long postId, CommentDto dto) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Not found"));
        post.getComments().add(this.mapper.map(dto, Comment.class));
        return dto;
    }

    @Override
    public void delete(long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CommentDto update(long id, CommentDto dto) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        comment.setName(dto.getName());
        return dto;
    }
}
