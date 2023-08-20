package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;

import java.util.List;

public interface PrestataireService {
    PrestataireDTO addPrestataire(PrestataireDTO prestataireDTO);
    PrestataireDTO updatePrestataire(PrestataireDTO prestataireDTO);
    void deletePrestataire(Long id);
    PrestataireDTO findPrestataire(Long id);
    List<PrestataireDTO> prestataireList();
}
