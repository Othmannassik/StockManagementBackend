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
        materiel.setTypeMateriel(typeMaterielMapper.toTypeMateriel(materielDTO.getTypeMaterielDTO()));
        materiel.setEtablissement(etablissementMapper.toEtablissement(materielDTO.getEtablissementDTO()));
        return materiel;
    }

    public MaterielDTO toMaterielDTO(Materiel materiel){
        MaterielDTO materielDTO = new MaterielDTO();
        BeanUtils.copyProperties(materiel,materielDTO);
        materielDTO.setTypeMaterielDTO(typeMaterielMapper.toTypeMaterielDTO(materiel.getTypeMateriel()));
        materielDTO.setEtablissementDTO(etablissementMapper.toEtablissementDTO(materiel.getEtablissement()));
        return materielDTO;
    }
}
