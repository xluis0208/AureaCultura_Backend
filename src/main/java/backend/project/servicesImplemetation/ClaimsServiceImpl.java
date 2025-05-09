package backend.project.servicesImplemetation;

import backend.project.entities.Claims;
import backend.project.entities.Client;
import backend.project.entities.Comment;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClaimsRepository;
import backend.project.repositories.ClientRepository;
import backend.project.services.ClaimsService;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsServiceImpl implements ClaimsService {

    @Autowired
    private ClaimsRepository claimsRepository;

    @Autowired
    private ClientRepository clienterepository;

    @Override
    public Claims insertClaim(Claims claims) {
        return claimsRepository.save(claims);
    }

    @Override
    public Claims insertClaim(String Tittle, String description, Long clientId) {

        Client clientaux = new Client();
        clientaux = clienterepository.findByUserId(clientId);

        Claims claims = new Claims();
        claims.setTitle(Tittle);
        claims.setDescription(description);
        claims.setClient(clientaux);
        return claimsRepository.save(claims);
    }

    @Override
    public void deleteClaim(Long id) {
        if (!claimsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Claim not found with the ID: " + id);
        }
        claimsRepository.deleteById(id);
    }

    @Override
    public List<Claims> listAllClaims() {
        return claimsRepository.findAll();
    }

    @Override
    public Claims findById(Long id) {
        return claimsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Claim not found with the ID: " + id));
    }

    @Override
    public List<Claims> findByTittleComment(String TittleAux) {
        return claimsRepository.findByTitle(TittleAux);
    }
}
