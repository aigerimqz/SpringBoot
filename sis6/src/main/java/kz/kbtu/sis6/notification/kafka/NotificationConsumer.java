package kz.kbtu.sis6.notification.kafka;

import kz.kbtu.sis6.event.PostCreatedEvent;
import kz.kbtu.sis6.notification.entity.Notification;
import kz.kbtu.sis6.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "posts", groupId = "notification-group")
    public void consume(PostCreatedEvent event) {
        log.info("Sending push notification to followers of user {} — new post {}",
                event.getUserId(), event.getPostId());

        Notification notification = Notification.builder()
                .id(UUID.randomUUID())
                .postId(UUID.fromString(event.getPostId()))
                .userId(event.getUserId())
                .message("New post from " + event.getUserId() + ": " + event.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        log.info("Notification saved for postId={}", event.getPostId());
    }
}