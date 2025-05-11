package backend.project.controllers;

import backend.project.entities.Expositor;
import backend.project.services.ExpositorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Inicio de codigo
import java.util.List;

@RestController
@RequestMapping("/api/expositor")
@CrossOrigin("*")
public class ExpositorController {

    @Autowired
    private ExpositorService expositorService;

    @PostMapping
    public ResponseEntity<Expositor> createExpositor(@RequestBody Expositor expositor) {
        Expositor savedExpositor = expositorService.insertExpositor(expositor);
        return new ResponseEntity<>(savedExpositor, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Expositor> createExpositor(@RequestParam String name) {
        Expositor savedExpositor = expositorService.insertExpositor(name);
        return new ResponseEntity<>(savedExpositor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpositor(@PathVariable Long id) {
        expositorService.deleteExpositor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Expositor>> listAllExpositor() {
        List<Expositor> expositorList = expositorService.listAllExpositor();
        if (expositorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(expositorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expositor> getExpositorById(@PathVariable Long id) {
        Expositor expositorFound = expositorService.findById(id);
        return new ResponseEntity<>(expositorFound, HttpStatus.OK);
    }

}
