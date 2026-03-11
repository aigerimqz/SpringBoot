package kz.kbtu.sis6.post.kafka;


import kz.kbtu.sis6.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostProducer {

    private static final String TOPIC = "posts";
    private final KafkaTemplate<String, PostCreatedEvent> kafkaTemplate;

    public void publish(PostCreatedEvent event){
        log.info("Publishing PostCreatedEvent to topic '{}': postId={}", TOPIC, event.getPostId());
        kafkaTemplate.send(TOPIC, event.getPostId(), event);
        log.info("Event published successfully for postId={}", event.getPostId());
    }
}
