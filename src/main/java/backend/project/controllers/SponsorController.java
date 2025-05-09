package backend.project.controllers;

import backend.project.entities.Expositor;
import backend.project.entities.Sponsor;
import backend.project.services.ExpositorService;
import backend.project.services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
@CrossOrigin("*")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @PostMapping
    public ResponseEntity<Sponsor> createSponsor(@RequestBody Sponsor sponsor) {
        Sponsor savedSponsor = sponsorService.insertSponsor(sponsor);
        return new ResponseEntity<>(savedSponsor, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Sponsor> createSponsor(@RequestParam String name) {
        Sponsor savedSponsor = sponsorService.insertSponsor(name);
        return new ResponseEntity<>(savedSponsor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteSponsor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Sponsor>> listAllSponsor() {
        List<Sponsor> sponsorList = sponsorService.listAllSponsor();
        if (sponsorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sponsorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable Long id) {
        Sponsor SponsorFound = sponsorService.findById(id);
        return new ResponseEntity<>(SponsorFound, HttpStatus.OK);
    }
}
