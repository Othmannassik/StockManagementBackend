package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.repositories.CommandeRepository;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    @Override
    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande updateCommande(Long id) {
        Commande commande = commandeRepository.findById(id).orElse(null);
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public Commande findCommande(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commande> commandeList() {
        return commandeRepository.findAll();
    }
}
