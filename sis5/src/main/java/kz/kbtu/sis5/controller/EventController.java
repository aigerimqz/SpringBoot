package kz.kbtu.sis5.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.kbtu.sis5.dto.EventRequestDTO;
import kz.kbtu.sis5.dto.EventResponseDTO;
import kz.kbtu.sis5.service.EventService;
import lombok.RequiredArgsConstructor;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event API", description = "Operations for managing events")
public class EventController {

    private final EventService eventService;


    @Operation(summary = "Get all events", description = "Returns list of all events")
    @ApiResponse(responseCode = "200", description = "List of events returned successfully")
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());

    }


    @Operation(summary = "Get event by ID")
    @ApiResponse(responseCode = "200", description = "Event found")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }


    @Operation(summary = "Create a new event")
    @ApiResponse(responseCode = "201", description = "Event created successfully")
    @ApiResponse(responseCode = "400", description = "Validation error in request body")
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody @Valid EventRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto));
    }

    @Operation(summary = "Update an event")
    @ApiResponse(responseCode = "200", description = "Event updated successfully")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @RequestBody @Valid EventRequestDTO dto) {

        return ResponseEntity.ok(eventService.updateEvent(id, dto));
    }


    @Operation(summary = "Delete an event")
    @ApiResponse(responseCode = "204", description = "Event deleted")
    @ApiResponse(responseCode = "404", description = "Event not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/paged")
    @Operation(summary = "Get paginated list of events")
    @ApiResponse(responseCode = "200", description = "Paginated list returned successfully")
    public ResponseEntity<Page<EventResponseDTO>> getEventsPaged(
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(eventService.getAllEvents(pageable));
    }


}