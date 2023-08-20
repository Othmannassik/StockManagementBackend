package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.mappers.PrestataireMapper;
import ma.cih.stockmanagementbackend.repositories.PrestataireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class PrestataireServiceImpl implements PrestataireService {
    private PrestataireRepository prestataireRepository;
    private PrestataireMapper prestataireMapper;
    @Override
    public PrestataireDTO addPrestataire(PrestataireDTO prestataireDTO) {
        Prestataire prestataire = prestataireMapper.toPrestataire(prestataireDTO);
        prestataireRepository.save(prestataire);
        return prestataireDTO;
    }

    @Override
    public PrestataireDTO updatePrestataire(PrestataireDTO prestataireDTO) {
        Prestataire prestataire = prestataireMapper.toPrestataire(prestataireDTO);
        prestataireRepository.save(prestataire);
        return prestataireDTO;
    }

    @Override
    public void deletePrestataire(Long id) {
        prestataireRepository.deleteById(id);
    }

    @Override
    public PrestataireDTO findPrestataire(Long id) {
        Prestataire prestataire = prestataireRepository.findById(id).orElse(null);
        return prestataireMapper.toPrestataireDTO(prestataire);
    }

    @Override
    public List<PrestataireDTO> prestataireList() {
        return prestataireRepository.findAll().stream()
                .map(pres -> prestataireMapper.toPrestataireDTO(pres))
                .toList();
    }
}
