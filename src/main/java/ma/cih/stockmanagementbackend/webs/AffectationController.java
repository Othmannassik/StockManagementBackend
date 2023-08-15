package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Affectation;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.services.interfaces.AffectationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("affectations")
public class AffectationController {
    private AffectationService affectationService;
    @PostMapping()
    public Affectation saveAffectation(@RequestBody Affectation affectation){
        return affectationService.addAffectation(affectation);
    }
    @PutMapping("/{affectationId}")
    public Affectation updateAffectation(@RequestBody Affectation affectation, @PathVariable Long affectationId){
        affectation.setIdAff(affectationId);
        return affectationService.updateAffectation(affectation);
    }
    @DeleteMapping("/{affectationId}")
    public void deleteAffectation(@PathVariable Long affectationId){
        affectationService.deleteAffectation(affectationId);
    }
    @GetMapping()
    public List<Affectation> affectationList(){
        return affectationService.affectationList();
    }
    @GetMapping("/{affectationId}")
    public Affectation getAffectation(@PathVariable Long affectationId){
        return affectationService.findAffectation(affectationId);
    }
}
