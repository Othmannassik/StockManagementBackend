package ma.cih.stockmanagementbackend.mappers;

import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProprietaireMapper {
    public Proprietaire toProprietaire(ProprietaireDTO proprietaireDTO){
        Proprietaire proprietaire = new Proprietaire();
        BeanUtils.copyProperties(proprietaireDTO,proprietaire);
        return proprietaire;
    }

    public ProprietaireDTO toProprietaireDTO(Proprietaire proprietaire){
        ProprietaireDTO proprietaireDTO = new ProprietaireDTO();
        BeanUtils.copyProperties(proprietaire,proprietaireDTO);
        return proprietaireDTO;
    }
}
