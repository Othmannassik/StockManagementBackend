package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;

import java.util.List;

public interface TypeMaterielService {
    TypeMaterielDTO addTypeMateriel(TypeMaterielDTO typeMaterielDTO);
    TypeMaterielDTO updateTypeMateriel(TypeMaterielDTO typeMaterielDTO);
    void deleteTypeMateriel(Long id);
    TypeMaterielDTO findTypeMateriel(Long id);
    List<TypeMaterielDTO> typeMaterielList();
}
