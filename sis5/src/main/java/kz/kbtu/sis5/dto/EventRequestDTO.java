package kz.kbtu.sis5.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for creating an event")
public class EventRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Schema(description = "Title of the event", example = "AI workshop")
    private String title;

    @Schema(description = "Location of the event", example = "Tole bi 59")
    private String location;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    @Schema(description = "Date and the time of the event", example = "2026-08-15T10:00:00")
    private LocalDateTime eventDate;

    @NotBlank
    @Email(message = "Must be a valid email")
    @Schema(description = "Organizer contact email", example = "organizer@kbtu.kz")
    private String organizerEmail;


    @Min(value = 0, message = "Ticket price cannot be negative")
    @Schema(description = "Ticket price in KZT", example = "1000.00")
    private BigDecimal ticketPrice;


    @Schema(description = "Optional event description", example = "A hands-on workshop")
    private String description;


}
