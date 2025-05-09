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
@Table(name = "Sponsor_Exclusive")

public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreSponsor;

    @OneToMany(mappedBy = "sponsor", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    private List<CulturalEvent> events = new ArrayList<>();;

    public Sponsor(Long id) {
        this.id = id;
    }

    public Sponsor(Long id, String nombreSponsor) {
        this.id = id;
        this.nombreSponsor = nombreSponsor;
    }
}
