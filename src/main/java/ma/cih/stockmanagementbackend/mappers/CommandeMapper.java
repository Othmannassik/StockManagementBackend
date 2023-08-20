package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommandeMapper {
    private MaterielMapper materielMapper;
    private PrestataireMapper prestataireMapper;
    public Commande toCommande(CommandeDTO commandeDTO){
        Commande commande = new Commande();
        BeanUtils.copyProperties(commandeDTO,commande);
        commande.setMateriel(materielMapper.toMateriel(commandeDTO.getMaterielDTO()));
        commande.setPrestataire(prestataireMapper.toPrestataire(commandeDTO.getPrestataireDTO()));
        return commande;
    }

    public CommandeDTO toCommandeDTO(Commande commande){
        CommandeDTO commandeDTO = new CommandeDTO();
        BeanUtils.copyProperties(commande,commandeDTO);
        commandeDTO.setMaterielDTO(materielMapper.toMaterielDTO(commande.getMateriel()));
        commandeDTO.setPrestataireDTO(prestataireMapper.toPrestataireDTO(commande.getPrestataire()));
        return commandeDTO;
    }
}
