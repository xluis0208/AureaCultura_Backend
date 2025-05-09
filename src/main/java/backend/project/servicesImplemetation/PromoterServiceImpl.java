package backend.project.servicesImplemetation;

import backend.project.entities.Promoter;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.PromoterRepository;
import backend.project.services.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoterServiceImpl implements PromoterService {

    @Autowired
    private PromoterRepository promoterRepository;

    @Override
    public Promoter insertPromoter(Promoter promoter) {
        return promoterRepository.save(promoter);
    }

    @Override
    public Promoter insertPromoter(String details) {
        Promoter promoter = new Promoter();
        promoter.setDetails(details);
        return promoterRepository.save(promoter);
    }

    @Override
    public void deletePromoter(Long id) {
        if (!promoterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Promoter not found with the ID: " + id);
        }
        promoterRepository.deleteById(id);
    }

    @Override
    public List<Promoter> listAllPromoters() {
        return promoterRepository.findAll();
    }

    @Override
    public Promoter findById(Long id) {
        return promoterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promoter not found with the ID: " + id));
    }

    @Override
    public List<Promoter> findByDetailsContaining(String keyword) {
        return promoterRepository.findByDetailsContaining(keyword);
    }
}
