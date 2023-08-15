package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("commandes")
public class CommandeController {
    private CommandeService commandeService;
    @PostMapping()
    public Commande saveCommande(@RequestBody Commande commande){
        return commandeService.addCommande(commande);
    }
    @PutMapping("/{commandeId}")
    public Commande updateCommande(@RequestBody Commande commande, @PathVariable Long commandeId){
        commande.setIdCmd(commandeId);
        return commandeService.updateCommande(commande);
    }
    @DeleteMapping("/{commandeId}")
    public void deleteCommande(@PathVariable Long commandeId){
        commandeService.deleteCommande(commandeId);
    }
    @GetMapping()
    public List<Commande> commandeList(){
        return commandeService.commandeList();
    }
    @GetMapping("/{commandeId}")
    public Commande getCommande(@PathVariable Long commandeId){
        return commandeService.findCommande(commandeId);
    }
}
