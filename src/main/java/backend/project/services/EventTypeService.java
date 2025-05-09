package backend.project.services;

import backend.project.entities.EventType;

import java.util.List;

public interface EventTypeService {
    EventType insertEventType(EventType eventType);
    EventType insertEventType(String theme, String description, String eventName);
    void deleteEventType(Long id);
    List<EventType> listAllEventTypes();
    EventType findById(Long id);
    List<EventType> findByTheme(String theme);
}
