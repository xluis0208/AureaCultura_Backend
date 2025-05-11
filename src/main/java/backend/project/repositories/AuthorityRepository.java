package backend.project.repositories;

import backend.project.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a FROM Authority a WHERE a.name = :name")
    Authority findByName(String name);
    //hola mundo
}
