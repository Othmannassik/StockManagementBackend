package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Commande;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommandeService {
    Commande addCommande(Commande commande);
    Commande updateCommande(Long id);
    void deleteCommande(Long id);
    Commande findCommande(Long id);
    List<Commande> commandeList();
}
