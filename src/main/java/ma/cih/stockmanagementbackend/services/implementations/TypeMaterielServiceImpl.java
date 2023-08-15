package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import ma.cih.stockmanagementbackend.repositories.TypeMaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class TypeMaterielServiceImpl implements TypeMaterielService {
    private TypeMaterielRepository typeMaterielRepository;
    @Override
    public TypeMateriel addTypeMateriel(TypeMateriel typeMateriel) {
        return typeMaterielRepository.save(typeMateriel);
    }

    @Override
    public TypeMateriel updateTypeMateriel(TypeMateriel typeMateriel) {
        return typeMaterielRepository.save(typeMateriel);
    }

    @Override
    public void deleteTypeMateriel(Long id) {
        typeMaterielRepository.deleteById(id);
    }

    @Override
    public TypeMateriel findTypeMateriel(Long id) {
        return typeMaterielRepository.findById(id).orElse(null);
    }

    @Override
    public List<TypeMateriel> typeMaterielList() {
        return typeMaterielRepository.findAll();
    }
}
