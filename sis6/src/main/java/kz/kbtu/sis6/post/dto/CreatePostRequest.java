package kz.kbtu.sis6.post.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body to create a new post")
public class CreatePostRequest {

    @NotBlank(message = "userId is required")
    @Schema(description = "ID of the user creating the post", example = "user-42")
    private String userId;


    @NotBlank(message = "content is required")
    @Size(max = 280, message = "content must not exceed 280 characters")
    @Schema(description = "Post content, max 280 characters", example = "Hello Kafka!")
    private String content;

    @Schema(description = "List of hashtags without #", example = "[\"kafka\", \"spring\"]")
    private List<String> hashtags;
}
