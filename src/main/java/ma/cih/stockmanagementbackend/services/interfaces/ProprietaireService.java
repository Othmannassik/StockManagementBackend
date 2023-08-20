package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;

import java.util.List;

public interface ProprietaireService {
    ProprietaireDTO addProprietaire(ProprietaireDTO proprietaireDTO);
    ProprietaireDTO updateProprietaire(ProprietaireDTO proprietaireDTO);
    void deleteProprietaire(Long id);
    ProprietaireDTO findProprietaire(Long id);
    List<ProprietaireDTO> proprietaireList();
}
