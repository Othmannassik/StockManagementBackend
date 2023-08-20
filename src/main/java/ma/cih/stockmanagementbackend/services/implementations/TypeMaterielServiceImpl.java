package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.mappers.TypeMaterielMapper;
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
    private TypeMaterielMapper typeMaterielMapper;
    @Override
    public TypeMaterielDTO addTypeMateriel(TypeMaterielDTO typeMaterielDTO) {
        TypeMateriel typeMateriel = typeMaterielMapper.toTypeMateriel(typeMaterielDTO);
        typeMaterielRepository.save(typeMateriel);
        return typeMaterielDTO;
    }

    @Override
    public TypeMaterielDTO updateTypeMateriel(TypeMaterielDTO typeMaterielDTO) {
        TypeMateriel typeMateriel = typeMaterielMapper.toTypeMateriel(typeMaterielDTO);
        typeMaterielRepository.save(typeMateriel);
        return typeMaterielDTO;
    }

    @Override
    public void deleteTypeMateriel(Long id) {
        typeMaterielRepository.deleteById(id);
    }

    @Override
    public TypeMaterielDTO findTypeMateriel(Long id) throws TypeMaterielNotFoundException {
        TypeMateriel typeMateriel = typeMaterielRepository.findById(id)
                .orElseThrow(() -> new TypeMaterielNotFoundException(String.format("TypeMateriel with id = %s Not Found", id)));
        return typeMaterielMapper.toTypeMaterielDTO(typeMateriel);
    }

    @Override
    public List<TypeMaterielDTO> typeMaterielList() {
        List<TypeMateriel> typeMaterielList = typeMaterielRepository.findAll();
        return typeMaterielList.stream()
                .map(type -> typeMaterielMapper.toTypeMaterielDTO(type))
                .toList();
    }
}
