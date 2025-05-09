package backend.project.services;

import backend.project.entities.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite insertFavorite(Favorite favorite);
    Favorite insertFavorite(Long clientId, Long eventId);
    void deleteFavorite(Long id);
    List<Favorite> listAllFavorites();
    Favorite findById(Long id);
    List<Favorite> findByClient(Long clientId);
}
