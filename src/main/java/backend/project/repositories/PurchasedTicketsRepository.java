package backend.project.repositories;

import backend.project.entities.PurchasedTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchasedTicketsRepository extends JpaRepository<PurchasedTickets, Long> {

    @Query("SELECT pt FROM PurchasedTickets pt WHERE pt.transaction.client.id = :clientId")
    List<PurchasedTickets> findByClient(@Param("clientId") Long clientId);
}
