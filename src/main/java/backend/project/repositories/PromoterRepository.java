package backend.project.repositories;

import backend.project.entities.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromoterRepository extends JpaRepository<Promoter, Long> {
    List<Promoter> findByDetailsContaining(String details);
}
