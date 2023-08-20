package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.mappers.CommandeMapper;
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
    private CommandeMapper commandeMapper;
    @Override
    public CommandeDTO addCommande(CommandeDTO commandeDTO) {
        Commande commande = commandeMapper.toCommande(commandeDTO);
        commandeRepository.save(commande);
        return commandeDTO;
    }

    @Override
    public CommandeDTO updateCommande(CommandeDTO commandeDTO) {
        Commande commande = commandeMapper.toCommande(commandeDTO);
        commandeRepository.save(commande);
        return commandeDTO;
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public CommandeDTO findCommande(Long id) throws CommandeNotFoundException {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException(String.format("Commande with id = %s Not Found", id)));
        return commandeMapper.toCommandeDTO(commande);
    }

    @Override
    public List<CommandeDTO> commandeList() {
        return commandeRepository.findAll().stream()
                .map(cmd -> commandeMapper.toCommandeDTO(cmd))
                .toList();
    }
}
