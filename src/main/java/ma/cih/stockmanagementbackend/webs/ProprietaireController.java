package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("proprietaires")
public class ProprietaireController {
    private ProprietaireService proprietaireService;
    @PostMapping()
    public Proprietaire saveProprietaire(@RequestBody Proprietaire proprietaire){
        return proprietaireService.addProprietaire(proprietaire);
    }
    @PutMapping("/{proprietaireId}")
    public Proprietaire updateProprietaire(@RequestBody Proprietaire proprietaire, @PathVariable Long proprietaireId){
        proprietaire.setIdProp(proprietaireId);
        return proprietaireService.updateProprietaire(proprietaire);
    }
    @DeleteMapping("/{proprietaireId}")
    public void deleteProprietaire(@PathVariable Long proprietaireId){
        proprietaireService.deleteProprietaire(proprietaireId);
    }
    @GetMapping()
    public List<Proprietaire> proprietaireList(){
        return proprietaireService.proprietaireList();
    }
    @GetMapping("/{proprietaireId}")
    public Proprietaire getProprietaire(@PathVariable Long proprietaireId){
        return proprietaireService.findProprietaire(proprietaireId);
    }
}
