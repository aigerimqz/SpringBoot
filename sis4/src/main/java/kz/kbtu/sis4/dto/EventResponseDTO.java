package kz.kbtu.sis4.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    private Long id;
    private String title;
    private String location;
    private LocalDateTime eventDate;
    private String organizerEmail;
    private BigDecimal ticketPrice;
    private String description;
}
