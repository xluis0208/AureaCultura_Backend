package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ticket_type")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer availableQuantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id")
    private CulturalEvent event;

    public TicketType(Long id, String name, Double price, Integer availableQuantity, CulturalEvent Cultevent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.event = Cultevent;
    }
}
