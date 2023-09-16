package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.entities.Affectation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AffectationMapper {
    private ProprietaireMapper proprietaireMapper;
    private MaterielMapper materielMapper;
    private MaterielDetailMapper materielDetailMapper;
    public Affectation toAffectation(AffectationDTO affectationDTO){
        Affectation affectation = new Affectation();
        BeanUtils.copyProperties(affectationDTO,affectation);
        affectation.setProprietaire(proprietaireMapper.toProprietaire(affectationDTO.getProprietaireDTO()));
        affectation.setMaterielDetail(materielDetailMapper.toMaterielDetail(affectationDTO.getMaterielDetailDTO()));
        return affectation;
    }

    public AffectationDTO toAffectationDTO(Affectation affectation){
        AffectationDTO affectationDTO = new AffectationDTO();
        BeanUtils.copyProperties(affectation,affectationDTO);
        affectationDTO.setProprietaireDTO(proprietaireMapper.toProprietaireDTO(affectation.getProprietaire()));
        affectationDTO.setMaterielDetailDTO(materielDetailMapper.toMaterielDetailDTO(affectation.getMaterielDetail()));
        return affectationDTO;
    }
}
