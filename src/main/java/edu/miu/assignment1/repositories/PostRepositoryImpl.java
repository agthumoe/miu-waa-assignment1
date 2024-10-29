package edu.miu.assignment1.repositories;

import edu.miu.assignment1.models.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private static final List<Post> posts = new ArrayList<>();

    static {
        posts.add(new Post(1L, "Exploring the Depths of the Ocean", "Discovering the mysteries of the underwater world and its diverse marine life.", "Arthur Curry"));
        posts.add(new Post(2L, "The Speed of Light vs. The Speed of Sound", "A breakdown of how speed influences everything from soundwaves to superhero feats.", "Barry Allen"));
        posts.add(new Post(3L, "Saving Metropolis: A Hero’s Guide", "An in-depth look at strategies for protecting a city, even one as big as Metropolis.", "Clark Kent"));
        posts.add(new Post(4L, "Behind the Mask: The Psychology of Superheroes", "An analysis of how superhero personas affect mental health and identity.", "Diana Prince"));
    }
    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public List<Post> findByAuthor(String author) {
        return posts.stream().filter((p) -> p.getAuthor().equals(author)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findByAuthorContaining(String author) {
        return posts.stream().filter(post -> post.getAuthor().contains(author)).collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(long id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(posts.size() + 1);
            posts.sort(Comparator.comparing(Post::getId));
            posts.stream().max(Comparator.comparing(Post::getId)).ifPresent((p) -> {
                post.setId(p.getId() + 1);
            });
        }
        posts.add(post);
        return post;
    }

    @Override
    public void delete(long id) {
        posts.removeIf(post -> post.getId() == id);
    }

    @Override
    public Post update(long id, Post post) {
        Optional<Post> optionalPost = this.findById(id);
        if (optionalPost.isPresent()) {
            Post updatedPost = optionalPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            updatedPost.setAuthor(post.getAuthor());
            return updatedPost;
        }
        return null;
    }
}
