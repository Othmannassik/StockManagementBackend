package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TypeMaterielService {
    TypeMateriel addTypeMateriel(TypeMateriel typeMateriel);
    TypeMateriel updateTypeMateriel(TypeMateriel typeMateriel);
    void deleteTypeMateriel(Long id);
    TypeMateriel findTypeMateriel(Long id);
    List<TypeMateriel> typeMaterielList();
}
