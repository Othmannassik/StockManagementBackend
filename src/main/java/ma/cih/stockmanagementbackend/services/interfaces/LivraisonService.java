package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;

import java.util.List;

public interface LivraisonService {
    LivraisonDTO addLivraison(LivraisonDTO livraisonDTO);
    LivraisonDTO updateLivraison(LivraisonDTO livraisonDTO);
    void deleteLivraison(Long id);
    LivraisonDTO findLivraison(Long id) throws LivraisonNotFoundException;
    List<LivraisonDTO> livraisonList();
}
