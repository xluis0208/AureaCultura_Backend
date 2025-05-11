package backend.project.repositories;
// cambios en este documento
import backend.project.entities.Expositor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpositorRepository extends JpaRepository<Expositor, Long> {
}
