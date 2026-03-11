package kz.kbtu.sis5.mapper;

import kz.kbtu.sis5.dto.EventRequestDTO;
import kz.kbtu.sis5.dto.EventResponseDTO;
import kz.kbtu.sis5.entity.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventRequestDTO dto);
    EventResponseDTO toResponseDTO(Event event);

    List<EventResponseDTO> toResponseDTOList (List<Event> events);
}
