package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.Etablissement;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("etablissements")
public class EtablissementController {
    private EtablissementService etablissementService;
    @PostMapping()
    public Etablissement saveEtablissement(@RequestBody Etablissement etablissement){
        return etablissementService.addEtablissement(etablissement);
    }
    @PutMapping("/{etablissementId}")
    public Etablissement updateEtablissement(@RequestBody Etablissement etablissement, @PathVariable Long etablissementId){
        etablissement.setIdEtb(etablissementId);
        return etablissementService.updateEtablissement(etablissement);
    }
    @DeleteMapping("/{etablissementId}")
    public void deleteEtablissement(@PathVariable Long etablissementId){
        etablissementService.deleteEtablissement(etablissementId);
    }
    @GetMapping()
    public List<Etablissement> etablissementList(){
        return etablissementService.etablissementList();
    }
    @GetMapping("/{etablissementId}")
    public Etablissement getEtablissement(@PathVariable Long etablissementId){
        return etablissementService.findEtablissement(etablissementId);
    }
}
