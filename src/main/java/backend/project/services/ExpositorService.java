package backend.project.services;

import backend.project.entities.Expositor;

import java.util.List;

public interface ExpositorService {
    Expositor insertExpositor(Expositor promoter);
    Expositor insertExpositor(String nameExpositor);
    void deleteExpositor(Long id);
    List<Expositor> listAllExpositor();
    Expositor findById(Long id);
}
