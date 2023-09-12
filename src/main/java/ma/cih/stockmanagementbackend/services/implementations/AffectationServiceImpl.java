package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.entities.Affectation;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.exceptions.AffectationNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.AffectationMapper;
import ma.cih.stockmanagementbackend.mappers.ProprietaireMapper;
import ma.cih.stockmanagementbackend.repositories.AffectationRepository;
import ma.cih.stockmanagementbackend.services.interfaces.AffectationService;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class AffectationServiceImpl implements AffectationService {
    private AffectationRepository affectationRepository;
    private AffectationMapper affectationMapper;
    private ProprietaireService proprietaireService;
    private ProprietaireMapper proprietaireMapper;
    @Override
    public AffectationDTO addAffectation(AffectationDTO affectationDTO) {
        Affectation affectation = affectationMapper.toAffectation(affectationDTO);
        affectationRepository.save(affectation);
        return affectationDTO;
    }

    @Override
    public AffectationDTO updateAffectation(AffectationDTO affectationDTO) {
        Affectation affectation = affectationMapper.toAffectation(affectationDTO);
        affectationRepository.save(affectation);
        return affectationDTO;
    }

    @Override
    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }

    @Override
    public AffectationDTO findAffectation(Long id) throws AffectationNotFoundException {
        Affectation affectation = affectationRepository.findById(id)
                .orElseThrow(() -> new AffectationNotFoundException(String.format("Affectation with id = %s Not Found", id)));
        return affectationMapper.toAffectationDTO(affectation);
    }

    @Override
    public List<AffectationDTO> affectationList() {
        return affectationRepository.findAll().stream()
                .map(aff -> affectationMapper.toAffectationDTO(aff))
                .toList();
    }

    @Override
    public List<AffectationDTO> affectationByProprieaire(Long id) throws ProprietaireNotFoundException {
        ProprietaireDTO proprietaire = proprietaireService.findProprietaire(id);
        return affectationRepository.findByProprietaire(proprietaireMapper.toProprietaire(proprietaire)).stream()
                .map(affectation -> affectationMapper.toAffectationDTO(affectation))
                .toList();
    }
}
