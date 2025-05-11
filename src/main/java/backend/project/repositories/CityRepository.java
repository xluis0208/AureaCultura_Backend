package backend.project.repositories;

import backend.project.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);

    //Repost
}
