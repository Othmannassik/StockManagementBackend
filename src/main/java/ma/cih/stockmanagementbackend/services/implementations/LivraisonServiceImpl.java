package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;
import ma.cih.stockmanagementbackend.mappers.LivraisonMapper;
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
    private LivraisonMapper livraisonMapper;
    @Override
    public LivraisonDTO addLivraison(LivraisonDTO livraisonDTO) {
        Livraison livraison = livraisonMapper.toLivraison(livraisonDTO);
        livraisonRepository.save(livraison);
        return livraisonDTO;
    }

    @Override
    public LivraisonDTO updateLivraison(LivraisonDTO livraisonDTO) {
        Livraison livraison = livraisonMapper.toLivraison(livraisonDTO);
        livraisonRepository.save(livraison);
        return livraisonDTO;
    }

    @Override
    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }

    @Override
    public LivraisonDTO findLivraison(Long id) throws LivraisonNotFoundException {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new LivraisonNotFoundException(String.format("Livraison with id = %s Not Found", id)));
        return livraisonMapper.toLivraisonDTO(livraison);
    }

    @Override
    public List<LivraisonDTO> livraisonList() {
        return livraisonRepository.findAll().stream()
                .map(liv -> livraisonMapper.toLivraisonDTO(liv))
                .toList();
    }
}
