package kz.kbtu.sis6.feed.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.kbtu.sis6.feed.entity.FeedItem;
import kz.kbtu.sis6.feed.repository.FeedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
@Tag(name = "Feed API", description = "Retrieve feed items for a user")
public class FeedController {

    private final FeedItemRepository feedItemRepository;

    @Operation(summary = "Get feed for a user")
    @ApiResponse(responseCode = "200", description = "Feed items returned")
    @Parameter(name = "userId", description = "The user ID to get the feed for", example = "user-42")
    @GetMapping
    public ResponseEntity<List<FeedItem>> getFeed(@RequestParam String userId) {
        return ResponseEntity.ok(feedItemRepository.findByUserId(userId));
    }
}