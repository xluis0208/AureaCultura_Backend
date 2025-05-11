package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//updated
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_type")
public class EventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private String description;
    private String eventName;

    @JsonIgnore
    @OneToMany(mappedBy = "eventType", fetch = FetchType.EAGER)
    private List<CulturalEvent> events;

    public EventType(Long id) {
        this.id = id;
    }

    public EventType(Long id, String theme, String description, String eventName) {
        this.id = id;
        this.theme = theme;
        this.description = description;
        this.eventName = eventName;
    }
}
