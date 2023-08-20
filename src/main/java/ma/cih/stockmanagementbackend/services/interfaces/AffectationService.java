package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.exceptions.AffectationNotFoundException;

import java.util.List;

public interface AffectationService {
    AffectationDTO addAffectation(AffectationDTO affectationDTO);
    AffectationDTO updateAffectation(AffectationDTO affectationDTO);
    void deleteAffectation(Long id);
    AffectationDTO findAffectation(Long id) throws AffectationNotFoundException;
    List<AffectationDTO> affectationList();
}
