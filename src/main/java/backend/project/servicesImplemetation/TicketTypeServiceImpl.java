package backend.project.servicesImplemetation;

import backend.project.entities.CulturalEvent;
import backend.project.entities.TicketType;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CulturalEventRepository;
import backend.project.repositories.TicketTypeRepository;
import backend.project.services.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private CulturalEventRepository culturalEventRepository;

    @Override
    public TicketType insertTicketType(TicketType ticketType) {
        return ticketTypeRepository.save(ticketType);
    }

    @Override
    public TicketType insertTicketType(String name, Double price, Integer availableQuantity, Long eventId) {
        TicketType ticketType = new TicketType();
        ticketType.setName(name);
        ticketType.setPrice(price);
        ticketType.setAvailableQuantity(availableQuantity);

        CulturalEvent eventaux = culturalEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));

        ticketType.setEvent(eventaux);
        return ticketTypeRepository.save(ticketType);
    }

    @Override
    public void deleteTicketType(Long id) {
        if (!ticketTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("TicketType not found with ID: " + id);
        }
        ticketTypeRepository.deleteById(id);
    }

    @Override
    public List<TicketType> listAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public TicketType findById(Long id) {
        return ticketTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TicketType not found with ID: " + id));
    }

    @Override
    public List<TicketType> findByEvent(Long eventId) {
        return ticketTypeRepository.findByEventId(eventId);
    }
}
