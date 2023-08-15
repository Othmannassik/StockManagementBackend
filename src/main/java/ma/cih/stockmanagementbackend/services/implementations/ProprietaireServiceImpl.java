package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.repositories.ProprietaireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class ProprietaireServiceImpl implements ProprietaireService {
    private ProprietaireRepository proprietaireRepository;
    @Override
    public Proprietaire addProprietaire(Proprietaire proprietaire) {
        return proprietaireRepository.save(proprietaire);
    }

    @Override
    public Proprietaire updateProprietaire(Proprietaire proprietaire) {
        return proprietaireRepository.save(proprietaire);
    }

    @Override
    public void deleteProprietaire(Long id) {
        proprietaireRepository.deleteById(id);
    }

    @Override
    public Proprietaire findProprietaire(Long id) {
        return proprietaireRepository.findById(id).orElse(null);
    }

    @Override
    public List<Proprietaire> proprietaireList() {
        return proprietaireRepository.findAll();
    }
}
