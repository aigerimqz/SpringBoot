package kz.kbtu.sis6.post.service;


import kz.kbtu.sis6.event.PostCreatedEvent;
import kz.kbtu.sis6.post.dto.CreatePostRequest;
import kz.kbtu.sis6.post.dto.PostResponse;
import kz.kbtu.sis6.post.entity.Post;
import kz.kbtu.sis6.post.kafka.PostProducer;
import kz.kbtu.sis6.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostProducer postProducer;

    public PostResponse createPost(CreatePostRequest request) {
        log.info("Creating post for userId={}", request.getUserId());

        UUID postId = UUID.randomUUID();
        String hashtagsStr = request.getHashtags() != null
                ? String.join(",", request.getHashtags())
                : null;

        Post post = Post.builder()
                .id(postId)
                .userId(request.getUserId())
                .content(request.getContent())
                .hashtags(hashtagsStr)
                .status("PUBLISHED")
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
        log.info("Post saved to DB with id={}", postId);

        PostCreatedEvent event = new PostCreatedEvent(
                postId.toString(),
                request.getUserId(),
                request.getContent(),
                request.getHashtags(),
                post.getCreatedAt()
        );
        postProducer.publish(event);

        return toResponse(post);
    }

    public PostResponse getPostById(UUID id) {
        log.info("Fetching post with id={}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Post not found with id={}", id);
                    return new ResourceNotFoundException("Post not found with id: " + id);
                });
        return toResponse(post);
    }

    private PostResponse toResponse(Post post) {
        List<String> hashtags = post.getHashtags() != null
                ? List.of(post.getHashtags().split(","))
                : List.of();
        return new PostResponse(
                post.getId().toString(),
                post.getUserId(),
                post.getContent(),
                hashtags,
                post.getStatus(),
                post.getCreatedAt()
        );
    }
}
