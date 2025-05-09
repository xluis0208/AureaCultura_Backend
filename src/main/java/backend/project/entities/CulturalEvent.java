package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class CulturalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private String accessCode;
    private String image;
    private String nombre;
    private String descripcion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    //@JsonBackReference
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "promoter_id")
    private Promoter promoter;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "expositor_id")
    private Expositor expositor;



}
