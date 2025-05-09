package backend.project.servicesImplemetation;

import backend.project.entities.Promoter;
import backend.project.entities.Sponsor;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.SponsorRepository;
import backend.project.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorServiceImpl implements SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public Sponsor insertSponsor(Sponsor Sponsor) {
        return sponsorRepository.save(Sponsor);
    }

    @Override
    public Sponsor insertSponsor(String nombreSponsor) {
        Sponsor sponsor = new Sponsor();
        sponsor.setNombreSponsor(nombreSponsor);
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void deleteSponsor(Long id) {
        if (!sponsorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sponsor not found with the ID: " + id);
        }
        sponsorRepository.deleteById(id);
    }

    @Override
    public List<Sponsor> listAllSponsor() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor findById(Long id) {
        return sponsorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sponsor not found with the ID: " + id));
    }
}
