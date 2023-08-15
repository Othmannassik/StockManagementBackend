package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.repositories.PrestataireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class PrestataireServiceImpl implements PrestataireService {
    private PrestataireRepository prestataireRepository;
    @Override
    public Prestataire addPrestataire(Prestataire prestataire) {
        return prestataireRepository.save(prestataire);
    }

    @Override
    public Prestataire updatePrestataire(Long id) {
        Prestataire prestataire = prestataireRepository.findById(id).orElse(null);
        return prestataireRepository.save(prestataire);
    }

    @Override
    public void deletePrestataire(Long id) {
        prestataireRepository.deleteById(id);
    }

    @Override
    public Prestataire findPrestataire(Long id) {
        return prestataireRepository.findById(id).orElse(null);
    }

    @Override
    public List<Prestataire> prestataireList() {
        return prestataireRepository.findAll();
    }
}
