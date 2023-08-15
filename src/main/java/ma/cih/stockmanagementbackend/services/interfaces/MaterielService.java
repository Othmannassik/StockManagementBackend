package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Materiel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MaterielService {
    Materiel addMateriel(Materiel materiel);
    Materiel updateMateriel(Materiel materiel);
    void deleteMateriel(Long id);
    Materiel findMateriel(Long id);
    List<Materiel> materielList();
}
