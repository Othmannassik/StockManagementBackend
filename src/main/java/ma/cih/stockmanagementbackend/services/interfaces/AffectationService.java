package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.entities.Affectation;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.exceptions.AffectationNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.MaterielDetailNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;

import java.util.List;

public interface AffectationService {
    AffectationDTO addAffectation(AffectationDTO affectationDTO);
    AffectationDTO updateAffectation(AffectationDTO affectationDTO);
    void deleteAffectation(Long id) throws AffectationNotFoundException;
    AffectationDTO findAffectation(Long id) throws AffectationNotFoundException;
    List<AffectationDTO> affectationList();
    List<AffectationDTO> affectationByProprieaire(Long id) throws ProprietaireNotFoundException;
    int countByMaterielDetail(Long id) throws MaterielDetailNotFoundException;
}
