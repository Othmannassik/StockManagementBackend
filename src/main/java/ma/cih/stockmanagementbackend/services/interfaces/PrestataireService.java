package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PrestataireService {
    PrestataireDTO addPrestataire(PrestataireDTO prestataireDTO);
    PrestataireDTO updatePrestataire(PrestataireDTO prestataireDTO);
    void deletePrestataire(Long id);
    PrestataireDTO findPrestataire(Long id) throws PrestataireNotFoundException;
    List<PrestataireDTO> prestataireList();
    ByteArrayInputStream exportExcel();
}
