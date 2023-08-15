package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Proprietaire;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProprietaireService {
    Proprietaire addProprietaire(Proprietaire proprietaire);
    Proprietaire updateProprietaire(Long id);
    void deleteProprietaire(Long id);
    Proprietaire findProprietaire(Long id);
    List<Proprietaire> proprietaireList();
}
