package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livraisons")
@CrossOrigin
public class LivraisonController {
    private LivraisonService livraisonService;
    @PostMapping("/{commandeId}")
    public LivraisonDTO saveLivraison(@RequestBody LivraisonDTO livraisonDTO, @PathVariable Long commandeId) throws CommandeNotFoundException {
        return livraisonService.addLivraison(livraisonDTO, commandeId);
    }
    @PutMapping("/{livraisonId}/{commandeId}")
    public LivraisonDTO updateLivraison(@RequestBody LivraisonDTO livraisonDTO, @PathVariable Long livraisonId, @PathVariable Long commandeId) throws CommandeNotFoundException {
        livraisonDTO.setIdLiv(livraisonId);
        return livraisonService.updateLivraison(livraisonDTO, commandeId);
    }
    @DeleteMapping("/{livraisonId}")
    public void deleteLivraison(@PathVariable Long livraisonId){
        livraisonService.deleteLivraison(livraisonId);
    }
    @GetMapping()
    public List<LivraisonDTO> livraisonList(){
        return livraisonService.livraisonList();
    }
    @GetMapping("/{livraisonId}")
    public LivraisonDTO getLivraison(@PathVariable Long livraisonId) throws LivraisonNotFoundException {
        return livraisonService.findLivraison(livraisonId);
    }
    @GetMapping("/{livraisonId}/commande")
    public CommandeDTO cmdByLivraison(@PathVariable Long livraisonId) throws LivraisonNotFoundException {
        return livraisonService.cmdByLivraison(livraisonId);
    }
}
