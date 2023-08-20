package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;

import java.util.List;

public interface CommandeService {
    CommandeDTO addCommande(CommandeDTO commandeDTO);
    CommandeDTO updateCommande(CommandeDTO commandeDTO);
    void deleteCommande(Long id);
    CommandeDTO findCommande(Long id) throws CommandeNotFoundException;
    List<CommandeDTO> commandeList();
}
