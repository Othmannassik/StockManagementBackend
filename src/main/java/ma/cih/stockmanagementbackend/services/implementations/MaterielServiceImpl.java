package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class MaterielServiceImpl implements MaterielService {
    private MaterielRepository materielRepository;
    @Override
    public Materiel addMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }

    @Override
    public Materiel updateMateriel(Materiel materiel) {
        return materielRepository.save(materiel);
    }

    @Override
    public void deleteMateriel(Long id) {
        materielRepository.deleteById(id);
    }

    @Override
    public Materiel findMateriel(Long id) {
        return materielRepository.findById(id).orElse(null);
    }

    @Override
    public List<Materiel> materielList() {
        return materielRepository.findAll();
    }
}
