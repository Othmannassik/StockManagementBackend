package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;

import java.util.List;

public interface EtablissementService {
    EtablissementDTO addEtablissement(EtablissementDTO etablissementDTO);
    EtablissementDTO updateEtablissement(EtablissementDTO etablissementDTO);
    void deleteEtablissement(Long id);
    EtablissementDTO findEtablissement(Long id) throws EtablissementNotFoundException;
    List<EtablissementDTO> etablissementList();
}
