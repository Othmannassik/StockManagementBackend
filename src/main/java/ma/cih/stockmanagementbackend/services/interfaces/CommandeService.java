package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.Commande;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommandeService {
    Commande addCommande(Commande commande);
    Commande updateCommande(Commande commande);
    void deleteCommande(Long id);
    Commande findCommande(Long id);
    List<Commande> commandeList();
}
