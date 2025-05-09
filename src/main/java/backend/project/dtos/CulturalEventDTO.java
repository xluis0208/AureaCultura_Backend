package backend.project.dtos;

import backend.project.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CulturalEventDTO {
    private Long id;
    private Integer capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private String accessCode;
    private String image;
    private String name;
    private String description;
    private String city_id;
    private String event_type_id;
    private String promoter_id;
    private String sponsor_id;
    private String expositor_id;

}
