package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Etablissement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EtablissementService {
    Etablissement addEtablissement(Etablissement etablissement);
    Etablissement updateEtablissement(Long id);
    void deleteEtablissement(Long id);
    Etablissement findEtablissement(Long id);
    List<Etablissement> etablissementList();
}
