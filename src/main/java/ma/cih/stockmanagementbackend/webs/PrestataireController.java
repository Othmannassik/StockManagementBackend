package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("prestataires")
public class PrestataireController {
    private PrestataireService prestataireService;
    @PostMapping()
    public Prestataire savePrestataire(@RequestBody Prestataire prestataire){
        return prestataireService.addPrestataire(prestataire);
    }
    @PutMapping("/{prestataireId}")
    public Prestataire updatePrestataire(@RequestBody Prestataire prestataire, @PathVariable Long prestataireId){
        prestataire.setIdPres(prestataireId);
        return prestataireService.updatePrestataire(prestataire);
    }
    @DeleteMapping("/{prestataireId}")
    public void deletePrestataire(@PathVariable Long prestataireId){
        prestataireService.deletePrestataire(prestataireId);
    }
    @GetMapping()
    public List<Prestataire> prestataireList(){
        return prestataireService.prestataireList();
    }
    @GetMapping("/{prestataireId}")
    public Prestataire getPrestataire(@PathVariable Long prestataireId){
        return prestataireService.findPrestataire(prestataireId);
    }
}
