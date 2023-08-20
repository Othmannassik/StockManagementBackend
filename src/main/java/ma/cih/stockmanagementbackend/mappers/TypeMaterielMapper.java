package ma.cih.stockmanagementbackend.mappers;

import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeMaterielMapper {
    public TypeMateriel toTypeMateriel(TypeMaterielDTO typeMaterielDTO){
        TypeMateriel typeMateriel = new TypeMateriel();
        BeanUtils.copyProperties(typeMaterielDTO,typeMateriel);
        return typeMateriel;
    }

    public TypeMaterielDTO toTypeMaterielDTO(TypeMateriel typeMateriel){
        TypeMaterielDTO typeMaterielDTO = new TypeMaterielDTO();
        BeanUtils.copyProperties(typeMateriel,typeMaterielDTO);
        return typeMaterielDTO;
    }
}
