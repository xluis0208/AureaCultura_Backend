package backend.project.servicesImplemetation;

import backend.project.dtos.CulturalEventDTO;
import backend.project.entities.*;
import backend.project.exceptions.InvalidDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.*;
import backend.project.services.CityService;
import backend.project.services.CulturalEventService;
import backend.project.services.PromoterService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CulturalEventServiceImpl implements CulturalEventService {

    @Autowired
    private CulturalEventRepository culturalEventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private PromoterRepository promoterRepository;

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private ExpositorRepository expositorRepository;



    @Override
    public CulturalEvent insertEventDTO(CulturalEventDTO event) {
        // Validaciones de campos obligatorios
        if (event.getCapacity() == null) {
            throw new InvalidDataException("Capacity cannot be empty");
        }
        if (event.getStartDate() == null) {
            throw new InvalidDataException("Start date cannot be empty");
        }
        if (event.getEndDate() == null) {
            throw new InvalidDataException("End date cannot be empty");
        }
        if (event.getAddress() == null || event.getAddress().isBlank()) {
            throw new InvalidDataException("Address cannot be empty");
        }
        if (event.getAccessCode() == null || event.getAccessCode().isBlank()) {
            throw new InvalidDataException("Access code cannot be empty");
        }
        if (event.getImage() == null || event.getImage().isBlank()) {
            throw new InvalidDataException("Image cannot be empty");
        }
        if (event.getCity_id() == null || event.getCity_id() .isBlank()) {
            throw new InvalidDataException("City ID cannot be empty");
        }
        if (event.getEvent_type_id() == null || event.getEvent_type_id().isBlank()) {
            throw new InvalidDataException("Event type ID cannot be empty");
        }
        if (event.getPromoter_id() == null || event.getPromoter_id().isBlank()) {
            throw new InvalidDataException("Promoter ID cannot be empty");
        }
        if (event.getSponsor_id() == null || event.getSponsor_id().isBlank()) {
            throw new InvalidDataException("Sponsor ID cannot be empty");
        }
        if (event.getExpositor_id() == null || event.getExpositor_id().isBlank()) {
            throw new InvalidDataException("Expositor ID cannot be empty");
        }

        if (event.getDescription() == null || event.getDescription().isBlank()) {
            throw new InvalidDataException("Description cannot be empty");
        }
        if (event.getName() == null || event.getName().isBlank()) {
            throw new InvalidDataException("Name cannot be empty");
        }



        // Buscar entidades relacionadas
        City city = cityRepository.findById(Long.parseLong(event.getCity_id()))
                .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + event.getCity_id()));
        EventType eventType = eventTypeRepository.findById(Long.parseLong(event.getEvent_type_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Event type not found with ID: " + event.getEvent_type_id()));
        Promoter promoter = promoterRepository.findById(Long.parseLong(event.getPromoter_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Promoter not found with ID: " + event.getPromoter_id()));
        Sponsor sponsor = sponsorRepository.findById(Long.parseLong(event.getSponsor_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Sponsor not found with ID: " + event.getSponsor_id()));
        Expositor expositor = expositorRepository.findById(Long.parseLong(event.getExpositor_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Expositor not found with ID: " + event.getExpositor_id()));

        // Crear el evento
        CulturalEvent culturalEvent = new CulturalEvent();
        culturalEvent.setCapacity(event.getCapacity());
        culturalEvent.setStartDate(event.getStartDate());
        culturalEvent.setEndDate(event.getEndDate());
        culturalEvent.setAddress(event.getAddress());
        culturalEvent.setAccessCode(event.getAccessCode());
        culturalEvent.setImage(event.getImage());
        culturalEvent.setCity(city);
        culturalEvent.setEventType(eventType);
        culturalEvent.setPromoter(promoter);
        culturalEvent.setSponsor(sponsor);
        culturalEvent.setExpositor(expositor);
        culturalEvent.setDescripcion(event.getDescription());
        culturalEvent.setNombre(event.getName());

        // Guardar el evento
        return culturalEventRepository.save(culturalEvent);
    }




    @Override
    public CulturalEvent insertEvent(Integer capacity, LocalDateTime startDate, LocalDateTime endDate, String address, String accessCode, String image, Long cityId, Long eventTypeId, Long promoterId, Long SponsorId, Long ExpositorId) {


        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + cityId));
        EventType eventType = eventTypeRepository.findById(eventTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Event type not found with ID: " + eventTypeId));
        Promoter promoter = promoterRepository.findById(promoterId)
                .orElseThrow(() -> new ResourceNotFoundException("Promoter not found with ID: " + promoterId));
        Sponsor sponsor = sponsorRepository.findById(SponsorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sponsor not found with ID: " + SponsorId));
        Expositor expositor = expositorRepository.findById(ExpositorId)
                .orElseThrow(() -> new ResourceNotFoundException("Expositor not found with ID: " + ExpositorId));

        CulturalEvent event = new CulturalEvent();
        event.setCapacity(capacity);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setAddress(address);
        event.setAccessCode(accessCode);
        event.setImage(image);
        event.setCity(city);
        event.setEventType(eventType);
        event.setPromoter(promoter);
        event.setSponsor(sponsor);
        return culturalEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!culturalEventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cultural Event not found with ID: " + id);
        }
        culturalEventRepository.deleteById(id);
    }

    @Override
    public List<CulturalEvent> listAllEventsCulturales() {
        return culturalEventRepository.findAll();
    }

    @Override
    public CulturalEvent findById(Long id) {
        return culturalEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cultural Event not found with the ID: " + id));
    }

    @Override
    public List<CulturalEvent> findByCity(Long cityId) {
        return culturalEventRepository.findByCityId(cityId);
    }

    @Override
    public CulturalEvent updateEvent(Long id, CulturalEventDTO event) {

        //Evento existente
        CulturalEvent existingEvent = culturalEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cultural Event not found with ID: " + id));

        if (event.getCapacity() == null) {
            throw new InvalidDataException("Capacity cannot be empty");
        }
        if (event.getStartDate() == null) {
            throw new InvalidDataException("Start date cannot be empty");
        }
        if (event.getEndDate() == null) {
            throw new InvalidDataException("End date cannot be empty");
        }
        if (event.getAddress() == null || event.getAddress().isBlank()) {
            throw new InvalidDataException("Address cannot be empty");
        }
        if (event.getAccessCode() == null || event.getAccessCode().isBlank()) {
            throw new InvalidDataException("Access code cannot be empty");
        }
        if (event.getImage() == null || event.getImage().isBlank()) {
            throw new InvalidDataException("Image cannot be empty");
        }
        if (event.getCity_id() == null || event.getCity_id().isBlank()) {
            throw new InvalidDataException("City ID cannot be empty");
        }
        if (event.getEvent_type_id() == null || event.getEvent_type_id().isBlank()) {
            throw new InvalidDataException("Event type ID cannot be empty");
        }
        if (event.getPromoter_id() == null || event.getPromoter_id().isBlank()) {
            throw new InvalidDataException("Promoter ID cannot be empty");
        }
        if (event.getSponsor_id() == null || event.getSponsor_id().isBlank()) {
            throw new InvalidDataException("Sponsor ID cannot be empty");
        }
        if (event.getExpositor_id() == null || event.getExpositor_id().isBlank()) {
            throw new InvalidDataException("Expositor ID cannot be empty");
        }
        if (event.getDescription() == null || event.getDescription().isBlank()) {
            throw new InvalidDataException("Description cannot be empty");
        }
        if (event.getName() == null || event.getName().isBlank()) {
            throw new InvalidDataException("Name cannot be empty");
        }

        // Buscar entidades relacionadas
        City city = cityRepository.findById(Long.parseLong(event.getCity_id()))
                .orElseThrow(() -> new ResourceNotFoundException("City not found with ID: " + event.getCity_id()));
        EventType eventType = eventTypeRepository.findById(Long.parseLong(event.getEvent_type_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Event type not found with ID: " + event.getEvent_type_id()));
        Promoter promoter = promoterRepository.findById(Long.parseLong(event.getPromoter_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Promoter not found with ID: " + event.getPromoter_id()));
        Sponsor sponsor = sponsorRepository.findById(Long.parseLong(event.getSponsor_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Sponsor not found with ID: " + event.getSponsor_id()));
        Expositor expositor = expositorRepository.findById(Long.parseLong(event.getExpositor_id()))
                .orElseThrow(() -> new ResourceNotFoundException("Expositor not found with ID: " + event.getExpositor_id()));

        // Actualizar los campos del evento existente
        existingEvent.setCapacity(event.getCapacity());
        existingEvent.setStartDate(event.getStartDate());
        existingEvent.setEndDate(event.getEndDate());
        existingEvent.setAddress(event.getAddress());
        existingEvent.setAccessCode(event.getAccessCode());
        existingEvent.setImage(event.getImage());
        existingEvent.setCity(city);
        existingEvent.setEventType(eventType);
        existingEvent.setPromoter(promoter);
        existingEvent.setSponsor(sponsor);
        existingEvent.setExpositor(expositor);
        existingEvent.setDescripcion(event.getDescription());
        existingEvent.setNombre(event.getName());

        return culturalEventRepository.save(existingEvent);
    }
}
