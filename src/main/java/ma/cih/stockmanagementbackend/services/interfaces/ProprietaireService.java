package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ProprietaireService {
    ProprietaireDTO addProprietaire(ProprietaireDTO proprietaireDTO);
    ProprietaireDTO updateProprietaire(ProprietaireDTO proprietaireDTO);
    void deleteProprietaire(Long id);
    ProprietaireDTO findProprietaire(Long id) throws ProprietaireNotFoundException;
    List<ProprietaireDTO> proprietaireList();
    ByteArrayInputStream exportExcel();
}
