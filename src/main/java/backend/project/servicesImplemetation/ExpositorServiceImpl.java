package backend.project.servicesImplemetation;

import backend.project.entities.Expositor;
import backend.project.entities.Promoter;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ExpositorRepository;
import backend.project.services.ExpositorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpositorServiceImpl implements ExpositorService {

    @Autowired
    private ExpositorRepository expositorRepository;

    @Override
    public Expositor insertExpositor(Expositor promoter) {
        return expositorRepository.save(promoter);
    }

    @Override
    public Expositor insertExpositor(String nameExpositor) {
        Expositor expositor = new Expositor();
        expositor.setNameExpositor(nameExpositor);
        return expositorRepository.save(expositor);
    }

    @Override
    public void deleteExpositor(Long id) {
        if (!expositorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Promoter not found with the ID: " + id);
        }
        expositorRepository.deleteById(id);
    }

    @Override
    public List<Expositor> listAllExpositor() {
        return expositorRepository.findAll();
    }

    @Override
    public Expositor findById(Long id) {
        return expositorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Promoter not found with the ID: " + id));
    }

}
