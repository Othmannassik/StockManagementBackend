package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Livraison;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LivraisonService {
    Livraison addLivraison(Livraison livraison);
    Livraison updateLivraison(Livraison livraison);
    void deleteLivraison(Long id);
    Livraison findLivraison(Long id);
    List<Livraison> livraisonList();
}
