package backend.project.repositories;

import backend.project.entities.Claims;
import backend.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimsRepository extends JpaRepository<Claims, Long> {
    @Query("SELECT c FROM Claims c WHERE c.client.id = :clientId")
    List<Claims> findByClient_Id(Long clientId);

    @Query("SELECT c FROM Claims c WHERE c.Title = :TitleAux")
    List<Claims> findByTitle(String TitleAux);


}
