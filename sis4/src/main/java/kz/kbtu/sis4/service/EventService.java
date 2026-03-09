package kz.kbtu.sis4.service;

import kz.kbtu.sis4.dto.EventRequestDTO;
import kz.kbtu.sis4.dto.EventResponseDTO;
import kz.kbtu.sis4.entity.Event;
import kz.kbtu.sis4.mapper.EventMapper;
import kz.kbtu.sis4.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventResponseDTO> getAllEvents(){
        log.info("Fetching all events");
        List<Event> events = eventRepository.findAll();
        log.debug("Found {} events", events.size());
        return eventMapper.toResponseDTOList(events);
    }

    public EventResponseDTO getEventById(Long id){
        log.info("Fetching event with id: {}", id);
        Event event = eventRepository.findById(id).orElseThrow(() -> {
            log.error("Event not found with id: {}", id);
            return new ResourceAccessException("Event not found");
        });
        return eventMapper.toResponseDTO(event);

    }


    public EventResponseDTO createEvent(EventRequestDTO dto){
        log.info("Creating new event: {}", dto.getTitle());
        Event event = eventMapper.toEntity(dto);
        Event saved = eventRepository.save(event);
        log.info("Event created successfully with id: {}", saved.getId());
        return eventMapper.toResponseDTO(saved);
    }

    public void deleteEvent(Long id){
        log.info("Deleting event with id: {}", id);
        if(!eventRepository.existsById(id)){
            log.error("Cannot delete, event not found: {}", id);
            throw new ResourceAccessException("Event not found");
        }

        eventRepository.deleteById(id);
        log.info("Event {} deleted", id);
    }
}
