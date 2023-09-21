package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface LivraisonService {
    LivraisonDTO addLivraison(LivraisonDTO livraisonDTO, Long commadeId) throws CommandeNotFoundException;
    LivraisonDTO updateLivraison(LivraisonDTO livraisonDTO, Long commandeId) throws CommandeNotFoundException;
    void deleteLivraison(Long id);
    LivraisonDTO findLivraison(Long id) throws LivraisonNotFoundException;
    List<LivraisonDTO> livraisonList();
    CommandeDTO cmdByLivraison(Long id) throws LivraisonNotFoundException;
    ByteArrayInputStream exportExcel();
}
