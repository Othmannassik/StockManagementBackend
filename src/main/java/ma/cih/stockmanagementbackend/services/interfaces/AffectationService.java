package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Affectation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AffectationService {
    Affectation addAffectation(Affectation affectation);
    Affectation updateAffectation(Long id);
    void deleteAffectation(Long id);
    Affectation findAffectation(Long id);
    List<Affectation> affectationList();
}
