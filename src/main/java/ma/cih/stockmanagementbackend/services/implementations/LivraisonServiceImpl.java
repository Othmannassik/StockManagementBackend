package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.repositories.LivraisonRepository;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private LivraisonRepository livraisonRepository;
    @Override
    public Livraison addLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    @Override
    public Livraison updateLivraison(Long id) {
        Livraison livraison = livraisonRepository.findById(id).orElse(null);
        return livraisonRepository.save(livraison);
    }

    @Override
    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }

    @Override
    public Livraison findLivraison(Long id) {
        return livraisonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Livraison> livraisonList() {
        return livraisonRepository.findAll();
    }
}
