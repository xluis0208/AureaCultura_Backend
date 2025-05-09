package backend.project.services;

import backend.project.entities.Authority;

import java.util.List;

public interface AuthorityService {
    Authority insertAuthority(Authority authority);
    Authority insertAuthority(String name);
    void deleteAuthority(Long id);
    List<Authority> listAllAuthorities();
    Authority findById(Long id);
}
