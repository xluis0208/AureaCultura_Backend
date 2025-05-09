package backend.project.services;

import backend.project.entities.City;

import java.util.List;

public interface CityService {
    City insertCity(City city);
    City insertCity(String name);
    void deleteCity(Long id);
    List<City> listAllCities();
    City findById(Long id);
    City findByName(String name);
}
