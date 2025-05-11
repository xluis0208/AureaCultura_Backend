package backend.project.repositories;
// cambios en este documento
import backend.project.entities.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;

//Now fixed
import java.util.List;

public interface PromoterRepository extends JpaRepository<Promoter, Long> {
    List<Promoter> findByDetailsContaining(String details);
}
