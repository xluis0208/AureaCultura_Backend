package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Hola mundo

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonManagedReference
    private List<CulturalEvent> events;

    public City(Long id) {
        this.id = id;
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
