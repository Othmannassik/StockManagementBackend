package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.ProprietaireMapper;
import ma.cih.stockmanagementbackend.repositories.ProprietaireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class ProprietaireServiceImpl implements ProprietaireService {
    private ProprietaireRepository proprietaireRepository;
    private ProprietaireMapper proprietaireMapper;
    @Override
    public ProprietaireDTO addProprietaire(ProprietaireDTO proprietaireDTO) {
        Proprietaire proprietaire = proprietaireMapper.toProprietaire(proprietaireDTO);
        proprietaireRepository.save(proprietaire);
        return proprietaireDTO;
    }

    @Override
    public ProprietaireDTO updateProprietaire(ProprietaireDTO proprietaireDTO) {
        Proprietaire proprietaire = proprietaireMapper.toProprietaire(proprietaireDTO);
        proprietaireRepository.save(proprietaire);
        return proprietaireDTO;
    }

    @Override
    public void deleteProprietaire(Long id) {
        proprietaireRepository.deleteById(id);
    }

    @Override
    public ProprietaireDTO findProprietaire(Long id) throws ProprietaireNotFoundException {
        Proprietaire proprietaire = proprietaireRepository.findById(id)
                .orElseThrow(() -> new ProprietaireNotFoundException(String.format("Proprietaire with id = %s Not Found", id)));
        return proprietaireMapper.toProprietaireDTO(proprietaire);
    }

    @Override
    public List<ProprietaireDTO> proprietaireList() {
        return proprietaireRepository.findAll().stream()
                .map(prop -> proprietaireMapper.toProprietaireDTO(prop))
                .toList();
    }
}
