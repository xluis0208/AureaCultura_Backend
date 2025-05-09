package backend.project.controllers;

import backend.project.entities.Claims;
import backend.project.entities.Comment;
import backend.project.services.ClaimsService;
import backend.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin("*")
public class ClaimsController {

    @Autowired
    private ClaimsService claimsService;

    @PostMapping
    public ResponseEntity<Claims> createClaim(@RequestBody Claims claims) {
        Claims savedClaim = claimsService.insertClaim(claims);
        return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Claims> createClaimByDetails(@RequestParam String Tittle, @RequestParam String description, @RequestParam Long clientId) {
        Claims savedClaim = claimsService.insertClaim(Tittle, description, clientId);
        return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        claimsService.deleteClaim(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Claims>> listAllClaims() {
        List<Claims> claims = claimsService.listAllClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claims> getClaimById(@PathVariable Long id) {
        Claims claim = claimsService.findById(id);
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<Claims>> getClaimByTittle(@RequestParam String Tittle) {
        List<Claims> claim = claimsService.findByTittleComment(Tittle);
        if (Tittle.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }
}
