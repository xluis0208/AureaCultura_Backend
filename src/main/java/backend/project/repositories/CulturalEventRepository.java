package backend.project.repositories;

import backend.project.entities.CulturalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CulturalEventRepository extends JpaRepository<CulturalEvent, Long> {
    @Query("SELECT e FROM CulturalEvent e WHERE e.startDate BETWEEN :startDate AND :endDate")
    List<CulturalEvent> findEventsBetweenDates(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);

    @Query("SELECT e FROM CulturalEvent e WHERE e.city.id = :cityId")
    List<CulturalEvent> findByCityId(@Param("cityId") Long cityId);


    List<CulturalEvent> findByExpositor_Id(Long expositorId);
}
