package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;

import java.util.List;

public interface LivraisonService {
    LivraisonDTO addLivraison(LivraisonDTO livraisonDTO);
    LivraisonDTO updateLivraison(LivraisonDTO livraisonDTO);
    void deleteLivraison(Long id);
    LivraisonDTO findLivraison(Long id);
    List<LivraisonDTO> livraisonList();
}
