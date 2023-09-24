package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.enums.StatusCmd;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CommandeService {
    CommandeDTO addCommande(CommandeDTO commandeDTO);
    CommandeDTO updateCommande(CommandeDTO commandeDTO);
    void deleteCommande(Long id);
    CommandeDTO findCommande(Long id) throws CommandeNotFoundException;
    List<CommandeDTO> commandeList();
    int nbCmdByPrestataire(Long id) throws PrestataireNotFoundException;
    ByteArrayInputStream exportExcel();
    int pendingCount();
}
