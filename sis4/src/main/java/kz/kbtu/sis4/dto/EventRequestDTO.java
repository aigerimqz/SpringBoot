package kz.kbtu.sis4.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String location;

    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;

    @NotBlank
    @Email(message = "Must be a valid email")
    private String organizerEmail;
    @Min(value = 0, message = "Ticket price cannot be negative")
    private BigDecimal ticketPrice;

    private String description;


}
