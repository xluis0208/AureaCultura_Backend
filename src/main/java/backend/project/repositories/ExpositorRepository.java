package backend.project.repositories;

import backend.project.entities.Expositor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpositorRepository extends JpaRepository<Expositor, Long> {
}
