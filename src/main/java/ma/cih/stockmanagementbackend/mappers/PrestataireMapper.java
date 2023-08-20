package ma.cih.stockmanagementbackend.mappers;

import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PrestataireMapper {
    public Prestataire toPrestataire(PrestataireDTO prestataireDTO){
        Prestataire prestataire = new Prestataire();
        BeanUtils.copyProperties(prestataireDTO,prestataire);
        return prestataire;
    }

    public PrestataireDTO toPrestataireDTO(Prestataire prestataire){
        PrestataireDTO prestataireDTO = new PrestataireDTO();
        BeanUtils.copyProperties(prestataire,prestataireDTO);
        return prestataireDTO;
    }
}
