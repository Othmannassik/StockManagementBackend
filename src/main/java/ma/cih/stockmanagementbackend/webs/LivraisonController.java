package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livraisons")
public class LivraisonController {
    private LivraisonService livraisonService;
    @PostMapping()
    public LivraisonDTO saveLivraison(@RequestBody LivraisonDTO livraisonDTO){
        return livraisonService.addLivraison(livraisonDTO);
    }
    @PutMapping("/{livraisonId}")
    public LivraisonDTO updateLivraison(@RequestBody LivraisonDTO livraisonDTO, @PathVariable Long livraisonId){
        livraisonDTO.setIdLiv(livraisonId);
        return livraisonService.updateLivraison(livraisonDTO);
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
}
