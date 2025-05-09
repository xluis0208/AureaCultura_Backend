package backend.project.services;

import backend.project.dtos.CulturalEventDTO;
import backend.project.entities.CulturalEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface CulturalEventService {
    CulturalEvent insertEventDTO(CulturalEventDTO culturalEventdto);
    CulturalEvent insertEvent(Integer capacity, LocalDateTime startDate, LocalDateTime endDate, String address, String accessCode, String image, Long cityId, Long eventTypeId, Long promoterId, Long SponsorId, Long ExpositorId);
    void deleteEvent(Long id);
    List<CulturalEvent> listAllEventsCulturales();
    CulturalEvent findById(Long id);
    List<CulturalEvent> findByCity(Long cityId);

    CulturalEvent updateEvent(Long id, CulturalEventDTO culturalEventDTO);
}
