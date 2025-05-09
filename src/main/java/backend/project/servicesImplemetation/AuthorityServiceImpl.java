package backend.project.servicesImplemetation;

import backend.project.entities.Authority;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.AuthorityRepository;
import backend.project.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority insertAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority insertAuthority(String name) {
        Authority authority = new Authority();
        authority.setName(name);
        return authorityRepository.save(authority);
    }

    @Override
    public void deleteAuthority(Long id) {
        if (!authorityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Authority not found with the ID: " + id);
        }
        authorityRepository.deleteById(id);
    }

    @Override
    public List<Authority> listAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(Long id) {
        return authorityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("Authority not found with the ID: " + id));
    }
}
