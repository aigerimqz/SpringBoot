package kz.kbtu.sis6.feed.kafka;


import kz.kbtu.sis6.event.PostCreatedEvent;
import kz.kbtu.sis6.feed.entity.FeedItem;
import kz.kbtu.sis6.feed.repository.FeedItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedConsumer {

    private final FeedItemRepository feedItemRepository;

    @KafkaListener(topics = "posts", groupId = "feed-group")
    public void consume(PostCreatedEvent event){
        if(event.getContent() == null || event.getContent().isBlank()){
            log.warn("! Skipping event {} - content is empty", event.getPostId());
            return;
        }

        log.info("Adding post {} by user {} to follower feeds - '{}'", event.getPostId(), event.getUserId(), event.getContent());


        String hashtagsStr = event.getHashtags() != null
                ? String.join(",", event.getHashtags())
                : null;

        FeedItem feedItem = FeedItem.builder()
                .id(UUID.randomUUID())
                .postId(UUID.fromString(event.getPostId()))
                .userId(event.getUserId())
                .content(event.getContent())
                .hashtags(hashtagsStr)
                .createdAt(event.getTimestamp())
                .build();

        feedItemRepository.save(feedItem);
        log.info("Feed item saved for postId={}", event.getPostId());
    }
}
