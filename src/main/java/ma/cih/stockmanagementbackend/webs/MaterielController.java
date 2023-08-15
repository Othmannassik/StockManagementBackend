package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("materiels")
public class MaterielController {
    private MaterielService materielService;
    @PostMapping()
    public Materiel saveMateriel(@RequestBody Materiel materiel){
        return materielService.addMateriel(materiel);
    }
    @PutMapping("/{materielId}")
    public Materiel updateMateriel(@RequestBody Materiel materiel, @PathVariable Long materielId){
        materiel.setIdMat(materielId);
        return materielService.updateMateriel(materiel);
    }
    @DeleteMapping("/{materielId}")
    public void deleteMateriel(@PathVariable Long materielId){
        materielService.deleteMateriel(materielId);
    }
    @GetMapping()
    public List<Materiel> materielList(){
        return materielService.materielList();
    }
    @GetMapping("/{materielId}")
    public Materiel getMateriel(@PathVariable Long materielId){
        return materielService.findMateriel(materielId);
    }
}
