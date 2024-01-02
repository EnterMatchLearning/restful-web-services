package com.example.restfulwebservices.user;

import com.example.restfulwebservices.exception.PostNotFoundException;
import com.example.restfulwebservices.exception.UserNotFoundException;
import com.example.restfulwebservices.exception.UserWithPostsException;
import com.example.restfulwebservices.jpa.PostRepository;
import com.example.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id:" + id));
        if (!user.getPosts().isEmpty()) {
            throw new UserWithPostsException("Can't delete a user with active posts. User ID: " + id);
        }
        userRepository.deleteById(id);
    }


    // GET /users/{id}/posts
    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id:" + id));
        return user.getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable UUID id, @RequestBody Post post) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id: " + id));
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedPost);
    }

    @GetMapping(path = "/jpa/users/{id}/posts/{postId}")
    public EntityModel<Post> retrievePost(@PathVariable UUID id, @PathVariable UUID postId) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user id: " + id));
        Post post = user.getPosts().stream().filter(p -> p.getId().equals(postId)).findFirst()
                .orElseThrow(() -> new PostNotFoundException("user id: " + id + ". post id: " + postId));

        EntityModel<Post> entityModel = EntityModel.of(post);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUser(post.getUser().getId()));
        entityModel.add(link.withRel("user"));

        return entityModel;
    }
}
