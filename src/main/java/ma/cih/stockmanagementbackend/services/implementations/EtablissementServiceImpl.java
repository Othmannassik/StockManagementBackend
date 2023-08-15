package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Etablissement;
import ma.cih.stockmanagementbackend.repositories.EtablissementRepository;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class EtablissementServiceImpl implements EtablissementService {
    private EtablissementRepository etablissementRepository;
    @Override
    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public Etablissement updateEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public void deleteEtablissement(Long id) {
        etablissementRepository.deleteById(id);
    }

    @Override
    public Etablissement findEtablissement(Long id) {
        return etablissementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Etablissement> etablissementList() {
        return etablissementRepository.findAll();
    }
}
