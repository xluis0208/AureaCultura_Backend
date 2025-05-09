package backend.project.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Expositor")
public class Expositor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameExpositor;
    @OneToMany(mappedBy = "expositor", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    private List<CulturalEvent> events = new ArrayList<>();

    public Expositor(Long id) {
        this.id = id;
    }

    public Expositor(Long id, String nameExpositor) {
        this.id = id;
        this.nameExpositor = nameExpositor;
    }
}
