package edu.miu.assignment.services;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.models.Comment;
import edu.miu.assignment.models.Post;
import edu.miu.assignment.models.dtos.CommentCreateDto;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.others.CustomMapper;
import edu.miu.assignment.repositories.CommentRepository;
import edu.miu.assignment.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CustomMapper mapper;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CustomMapper mapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> findByPostId(long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new HttpStatusException("Post not found", HttpStatus.NOT_FOUND));
        return this.mapper.map(post.getComments(), CommentDto.class);
    }

    @Override
    public List<CommentDto> findAll() {
        return this.mapper.map(commentRepository.findAll(), CommentDto.class);
    }

    @Override
    public CommentDto findById(long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(() -> new HttpStatusException("Comment not found", HttpStatus.NOT_FOUND));
        return this.mapper.map(comment, CommentDto.class);
    }

    @Override
    @Transactional
    public CommentDto save(long postId, CommentCreateDto dto) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new HttpStatusException("Post Not found", HttpStatus.NOT_FOUND));
        Comment comment = this.mapper.map(dto, Comment.class);
        post.getComments().add(comment);
        return this.mapper.map(comment, CommentDto.class);
    }

    @Override
    public void delete(long id) {
        this.commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CommentDto update(long id, CommentCreateDto dto) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(() -> new HttpStatusException("Comment Not found", HttpStatus.NOT_FOUND));
        comment.setName(dto.getName());
        return this.mapper.map(this.commentRepository.save(comment), CommentDto.class);
    }
}
