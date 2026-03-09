package kz.kbtu.sis4.mapper;

import kz.kbtu.sis4.dto.EventRequestDTO;
import kz.kbtu.sis4.dto.EventResponseDTO;
import kz.kbtu.sis4.entity.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventRequestDTO dto);
    EventResponseDTO toResponseDTO(Event event);

    List<EventResponseDTO> toResponseDTOList (List<Event> events);
}
