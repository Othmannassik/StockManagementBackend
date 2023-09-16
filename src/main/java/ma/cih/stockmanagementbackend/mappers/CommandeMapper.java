package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeMapper {
    private MaterielMapper materielMapper;
    private PrestataireMapper prestataireMapper;
    private LivraisonMapper livraisonMapper;
    private EtablissementMapper etablissementMapper;
    public Commande toCommande(CommandeDTO commandeDTO){
        Commande commande = new Commande();
        BeanUtils.copyProperties(commandeDTO,commande);
        commande.setMateriel(materielMapper.toMateriel(commandeDTO.getMateriel()));
        commande.setEtablissement(etablissementMapper.toEtablissement(commandeDTO.getEtablissement()));
        if(commandeDTO.getPrestataire() != null){
            commande.setPrestataire(prestataireMapper.toPrestataire(commandeDTO.getPrestataire()));
        }
        List<Livraison> livraisons = commandeDTO.getLivraisonList().stream()
                .map(livraisonDTO -> livraisonMapper.toLivraison(livraisonDTO))
                .toList();
        commande.setLivraisonList(livraisons);
        return commande;
    }

    public CommandeDTO toCommandeDTO(Commande commande){
        CommandeDTO commandeDTO = new CommandeDTO();
        BeanUtils.copyProperties(commande,commandeDTO);
        commandeDTO.setMateriel(materielMapper.toMaterielDTO(commande.getMateriel()));
        commandeDTO.setEtablissement(etablissementMapper.toEtablissementDTO(commande.getEtablissement()));
        if (commande.getPrestataire() != null){
            commandeDTO.setPrestataire(prestataireMapper.toPrestataireDTO(commande.getPrestataire()));
        }
        if(commande.getLivraisonList() != null){
            List<LivraisonDTO> livraisonDTOS = commande.getLivraisonList().stream()
                    .map(livraison -> livraisonMapper.toLivraisonDTO(livraison))
                    .toList();
            commandeDTO.setLivraisonList(livraisonDTOS);
        }
        return commandeDTO;
    }
}
