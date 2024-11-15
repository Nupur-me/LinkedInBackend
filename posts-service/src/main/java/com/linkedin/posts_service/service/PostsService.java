package com.linkedin.posts_service.service;

import com.linkedin.posts_service.auth.UserContextHolder;
import com.linkedin.posts_service.clients.ConnectionsClient;
import com.linkedin.posts_service.dto.PersonDTO;
import com.linkedin.posts_service.dto.PostCreateRequestDTO;
import com.linkedin.posts_service.dto.PostDTO;
import com.linkedin.posts_service.entity.Post;
import com.linkedin.posts_service.event.PostCreatedEvent;
import com.linkedin.posts_service.exception.ResourceNotFoundException;
import com.linkedin.posts_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {
    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;
    private final ConnectionsClient connectionsClient;

    private final KafkaTemplate<Long, PostCreatedEvent> kafkaTemplate;

    public PostDTO createPost(PostCreateRequestDTO postCreateRequestDTO) {

        Long userId = UserContextHolder.getCurrentUserId();
        Post post = modelMapper.map(postCreateRequestDTO, Post.class);
        post.setUserId(userId);

        Post savedPost = postsRepository.save(post);

        PostCreatedEvent postCreatedEvent = PostCreatedEvent.builder()
                .postId(savedPost.getId())
                .creatorId(userId)
                .content(savedPost.getContent())
                .build();

        kafkaTemplate.send("post-created-topic", postCreatedEvent);

        return modelMapper.map(savedPost, PostDTO.class);
    }

    public PostDTO getPostById(Long postId) {
        log.debug("Retrieving post with ID: {}", postId);

        Post post =  postsRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post Not Found with userId: "+postId));

        return modelMapper.map(post,PostDTO.class);
    }

    public List<PostDTO> getAllPostsOfUser(Long userId) {
        List<Post> posts = postsRepository.findByUserId(userId);
        return posts
                .stream()
                .map((element) -> modelMapper.map(element, PostDTO.class))
                .collect(Collectors.toList());
    }
}
