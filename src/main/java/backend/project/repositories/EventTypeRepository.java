package backend.project.repositories;
// cambios en este documento
import backend.project.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
    List<EventType> findByTheme(String theme);
}
