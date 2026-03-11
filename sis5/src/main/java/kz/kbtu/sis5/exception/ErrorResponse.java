package kz.kbtu.sis5.exception;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {

    @Schema(example = "404")
    private int status;

    @Schema(example = "Event not found")
    private String message;
}
