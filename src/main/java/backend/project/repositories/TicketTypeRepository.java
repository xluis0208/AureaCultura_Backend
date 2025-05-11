package backend.project.repositories;
// cambios en este documento
import backend.project.entities.Promoter;
import backend.project.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {


    @Query("SELECT tt FROM TicketType tt WHERE tt.event.id = :eventId")
    List<TicketType> findByEventId(@Param("eventId") Long eventId);
}
