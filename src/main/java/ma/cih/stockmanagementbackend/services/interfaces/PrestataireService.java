package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Prestataire;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PrestataireService {
    Prestataire addPrestataire(Prestataire prestataire);
    Prestataire updatePrestataire(Prestataire prestataire);
    void deletePrestataire(Long id);
    Prestataire findPrestataire(Long id);
    List<Prestataire> prestataireList();
}
