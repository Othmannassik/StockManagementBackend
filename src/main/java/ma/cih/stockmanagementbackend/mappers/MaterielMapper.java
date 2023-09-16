package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.entities.Materiel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterielMapper {
    private TypeMaterielMapper typeMaterielMapper;
    private EtablissementMapper etablissementMapper;
    public Materiel toMateriel(MaterielDTO materielDTO){
        Materiel materiel = new Materiel();
        BeanUtils.copyProperties(materielDTO,materiel);
        materiel.setTypeMateriel(typeMaterielMapper.toTypeMateriel(materielDTO.getTypeMateriel()));
        return materiel;
    }

    public MaterielDTO toMaterielDTO(Materiel materiel){
        MaterielDTO materielDTO = new MaterielDTO();
        BeanUtils.copyProperties(materiel,materielDTO);
        materielDTO.setTypeMateriel(typeMaterielMapper.toTypeMaterielDTO(materiel.getTypeMateriel()));
        return materielDTO;
    }
}
