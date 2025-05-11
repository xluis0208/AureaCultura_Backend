package backend.project.controllers;
// Cambios en el documento
import backend.project.dtos.CulturalEventDTO;
import backend.project.entities.*;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.services.CulturalEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/CulturalEvents")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CulturalEventController {

    @Autowired
    private CulturalEventService culturalEventService;

    @PostMapping("/Create")
    public ResponseEntity<CulturalEvent> addCulturalEvent(@RequestBody CulturalEventDTO culturalEventdto) {
        CulturalEvent newCulturalEvent = culturalEventService.insertEventDTO(culturalEventdto);

        if (newCulturalEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(newCulturalEvent, HttpStatus.CREATED);
    }



    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CulturalEvent> updateCulturalEvent(@PathVariable Long id, @RequestBody CulturalEventDTO culturalEventDTO) {
        try {
            CulturalEvent updatedEvent = culturalEventService.updateEvent(id, culturalEventDTO);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        culturalEventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CulturalEvent>> listAllEvents() {
        List<CulturalEvent> Culturalevents = culturalEventService.listAllEventsCulturales();
        if (Culturalevents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Culturalevents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CulturalEvent> getEventById(@PathVariable Long id) {
        CulturalEvent event = culturalEventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<CulturalEvent>> getEventsByCity(@PathVariable Long cityId) {
        List<CulturalEvent> events = culturalEventService.findByCity(cityId);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<CulturalEvent> updateEvent(@PathVariable Long id, @RequestBody CulturalEvent eventDetails) {
        CulturalEvent event = culturalEventService.findById(id);

        event.setCapacity(eventDetails.getCapacity());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setAddress(eventDetails.getAddress());
        event.setAccessCode(eventDetails.getAccessCode());
        event.setImage(eventDetails.getImage());

        CulturalEvent updatedEvent = culturalEventService.insertEvent(event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

     */

}
