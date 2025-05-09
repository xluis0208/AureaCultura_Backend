package backend.project.services;

import backend.project.entities.Promoter;

import java.util.List;

public interface PromoterService {

    Promoter insertPromoter(Promoter promoter);
    Promoter insertPromoter(String details);
    void deletePromoter(Long id);
    List<Promoter> listAllPromoters();
    Promoter findById(Long id);
    List<Promoter> findByDetailsContaining(String keyword);
}
