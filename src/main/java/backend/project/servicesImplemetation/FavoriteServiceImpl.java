package backend.project.servicesImplemetation;

import backend.project.entities.Client;
import backend.project.entities.CulturalEvent;
import backend.project.entities.Favorite;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.repositories.CulturalEventRepository;
import backend.project.repositories.FavoriteRepository;
import backend.project.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service Favorite Implementarion

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CulturalEventRepository culturalEventRepository;

    @Override
    public Favorite insertFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite insertFavorite(Long clientId, Long eventId) {
        Favorite favorite = new Favorite();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));
        CulturalEvent eventaux = culturalEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));

        favorite.setClient(client);
        favorite.setEvent(eventaux);

        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with the ID: " + id);
        }
        favoriteRepository.deleteById(id);
    }

    @Override
    public List<Favorite> listAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite findById(Long id) {
        return favoriteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + id));
    }

    @Override
    public List<Favorite> findByClient(Long clientId) {
        return favoriteRepository.findByClient_Id(clientId);
    }
}
