package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promoter")
public class Promoter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    @OneToMany(mappedBy = "promoter", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    private List<CulturalEvent> events;

    public Promoter(Long id) {
        this.id = id;
    }

    public Promoter(Long id, String details) {
        this.id = id;
        this.details = details;
    }
}
