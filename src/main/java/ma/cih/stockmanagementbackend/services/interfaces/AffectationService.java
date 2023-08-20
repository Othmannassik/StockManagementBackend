package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.AffectationDTO;

import java.util.List;

public interface AffectationService {
    AffectationDTO addAffectation(AffectationDTO affectationDTO);
    AffectationDTO updateAffectation(AffectationDTO affectationDTO);
    void deleteAffectation(Long id);
    AffectationDTO findAffectation(Long id);
    List<AffectationDTO> affectationList();
}
