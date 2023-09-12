package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.entities.Livraison;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LivraisonMapper {
    public Livraison toLivraison(LivraisonDTO livraisonDTO){
        Livraison livraison = new Livraison();
        BeanUtils.copyProperties(livraisonDTO,livraison);
        return livraison;
    }

    public LivraisonDTO toLivraisonDTO(Livraison livraison){
        LivraisonDTO livraisonDTO = new LivraisonDTO();
        BeanUtils.copyProperties(livraison,livraisonDTO);
        return livraisonDTO;
    }
}
