package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livraisons")
public class LivraisonController {
    private LivraisonService livraisonService;
    @PostMapping()
    public Livraison saveLivraison(@RequestBody Livraison livraison){
        return livraisonService.addLivraison(livraison);
    }
    @PutMapping("/{livraisonId}")
    public Livraison updateLivraison(@RequestBody Livraison livraison, @PathVariable Long livraisonId){
        livraison.setIdLiv(livraisonId);
        return livraisonService.updateLivraison(livraison);
    }
    @DeleteMapping("/{livraisonId}")
    public void deleteLivraison(@PathVariable Long livraisonId){
        livraisonService.deleteLivraison(livraisonId);
    }
    @GetMapping()
    public List<Livraison> livraisonList(){
        return livraisonService.livraisonList();
    }
    @GetMapping("/{livraisonId}")
    public Livraison getLivraison(@PathVariable Long livraisonId){
        return livraisonService.findLivraison(livraisonId);
    }
}
