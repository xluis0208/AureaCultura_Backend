package backend.project.repositories;
// cambios en este documento
import backend.project.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    @Query("SELECT f FROM Favorite f WHERE f.client.id = :clientId")
    List<Favorite> findByClient_Id(@Param("clientId") Long clientId);
}
