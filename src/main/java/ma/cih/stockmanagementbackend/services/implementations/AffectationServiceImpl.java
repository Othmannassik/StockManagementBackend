package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Affectation;
import ma.cih.stockmanagementbackend.repositories.AffectationRepository;
import ma.cih.stockmanagementbackend.services.interfaces.AffectationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class AffectationServiceImpl implements AffectationService {
    private AffectationRepository affectationRepository;
    @Override
    public Affectation addAffectation(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    @Override
    public Affectation updateAffectation(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    @Override
    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }

    @Override
    public Affectation findAffectation(Long id) {
        return affectationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Affectation> affectationList() {
        return affectationRepository.findAll();
    }
}
