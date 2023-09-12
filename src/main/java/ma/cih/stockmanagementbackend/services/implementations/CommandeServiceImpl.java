package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.CommandeMapper;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.mappers.PrestataireMapper;
import ma.cih.stockmanagementbackend.repositories.CommandeRepository;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    private CommandeMapper commandeMapper;
    private MaterielService materielService;
    private MaterielMapper materielMapper;
    private PrestataireService prestataireService;
    private PrestataireMapper prestataireMapper;
    @Override
    public CommandeDTO addCommande(CommandeDTO commandeDTO){
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

    @Override
    public int nbCmdByPrestataire(Long id) throws PrestataireNotFoundException {
        PrestataireDTO prestataireDTO = prestataireService.findPrestataire(id);
        return commandeRepository.countByPrestataire(prestataireMapper.toPrestataire(prestataireDTO));
    }
}
