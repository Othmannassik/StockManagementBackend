package ma.cih.stockmanagementbackend.mappers;

import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.entities.Etablissement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EtablissementMapper {
    public Etablissement toEtablissement(EtablissementDTO etablissementDTO){
        Etablissement etablissement = new Etablissement();
        BeanUtils.copyProperties(etablissementDTO,etablissement);
        return etablissement;
    }

    public EtablissementDTO toEtablissementDTO(Etablissement etablissement){
        EtablissementDTO etablissementDTO = new EtablissementDTO();
        BeanUtils.copyProperties(etablissement,etablissementDTO);
        return etablissementDTO;
    }
}
