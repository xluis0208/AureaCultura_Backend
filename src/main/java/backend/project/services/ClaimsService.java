package backend.project.services;

import backend.project.entities.Claims;
import backend.project.entities.Comment;

import java.util.List;

public interface ClaimsService {

    Claims insertClaim(Claims claims);
    Claims insertClaim(String Tittle, String description, Long clientId);
    void deleteClaim(Long id);
    List<Claims> listAllClaims();
    Claims findById(Long id);
    List<Claims> findByTittleComment(String TittleAux);
}
