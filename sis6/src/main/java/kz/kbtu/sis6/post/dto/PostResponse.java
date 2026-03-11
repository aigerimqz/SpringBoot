package kz.kbtu.sis6.post.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Post data returned to client")
public class PostResponse {

    @Schema(example = "abc-123")
    private String id;
    @Schema(example = "user-42")
    private String userId;
    @Schema(example = "Hello Kafka!")
    private String content;
    private List<String> hashtags;
    @Schema(example = "PUBLISHED")
    private String status;
    private LocalDateTime createdAt;

}
