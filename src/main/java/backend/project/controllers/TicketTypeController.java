package backend.project.controllers;

import backend.project.entities.TicketType;
import backend.project.services.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-types")
@CrossOrigin("*")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

    @PostMapping("/create")
    public ResponseEntity<TicketType> createTicketTypeByDetails(@RequestBody TicketType ticketType){
        System.out.println("REGISTRO ticket type");
        System.out.println(ticketType);
        TicketType savedTicketType = ticketTypeService.insertTicketType(ticketType);
        return new ResponseEntity<>(savedTicketType, HttpStatus.CREATED);
    }

    @PostMapping("/create-params")
    public ResponseEntity<TicketType> createTicketTypeByDetailsParams(@RequestParam String name, @RequestParam Double price,
                                                                      @RequestParam Integer availableQuantity, @RequestParam Long eventId) {
        TicketType savedTicketType = ticketTypeService.insertTicketType(name, price, availableQuantity, eventId);
        return new ResponseEntity<>(savedTicketType, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        ticketTypeService.deleteTicketType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TicketType>> listAllTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeService.listAllTicketTypes();
        System.out.println("TAMAÑO: "+ticketTypes.size());

        for(TicketType obj: ticketTypes) {
            System.out.println("MOSTRANDO: "+ obj.getPrice());
            System.out.println(obj.toString());
        }

        return new ResponseEntity<>(ticketTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketType> getTicketTypeById(@PathVariable Long id) {
        TicketType ticketType = ticketTypeService.findById(id);
        return new ResponseEntity<>(ticketType, HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TicketType>> getTicketTypesByEvent(@PathVariable Long eventId) {
        List<TicketType> ticketTypes = ticketTypeService.findByEvent(eventId);
        return new ResponseEntity<>(ticketTypes, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TicketType> editar(@RequestBody TicketType obj , @PathVariable("id") Long id) {
        obj.setId(id);
        TicketType saved = ticketTypeService.insertTicketType(obj);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
