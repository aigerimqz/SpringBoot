package kz.kbtu.sis5.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {

    @Schema(example = "1")
    private Long id;


    @Schema(example = "AI workshop")
    private String title;

    @Schema(example = "Tole bi 59")
    private String location;

    @Schema(example = "2026-08-15T10:00:00")
    private LocalDateTime eventDate;

    @Schema(example = "organizer@kbtu.kz")
    private String organizerEmail;

    @Schema(example = "1000.00")
    private BigDecimal ticketPrice;

    @Schema(example = "A hands-on workshop")
    private String description;
}
