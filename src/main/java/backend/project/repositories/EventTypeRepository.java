package backend.project.repositories;

import backend.project.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    List<EventType> findByTheme(String theme);
}
