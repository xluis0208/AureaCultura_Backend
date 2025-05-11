package backend.project.services;
// cambios en este documento
import backend.project.entities.Sponsor;

import java.util.List;

public interface SponsorService {

    Sponsor insertSponsor(Sponsor Sponsor);
    Sponsor insertSponsor(String nombreSponsor);
    void deleteSponsor(Long id);
    List<Sponsor> listAllSponsor();
    Sponsor findById(Long id);
}
