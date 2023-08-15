package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("typeMateriels")
public class TypeMaterielController {
    private TypeMaterielService typeMaterielService;
    @PostMapping()
    public TypeMateriel saveTypeMateriel(@RequestBody TypeMateriel typeMateriel){
        return typeMaterielService.addTypeMateriel(typeMateriel);
    }
    @PutMapping("/{typeMaterielId}")
    public TypeMateriel updateTypeMateriel(@RequestBody TypeMateriel typeMateriel, @PathVariable Long typeMaterielId){
        typeMateriel.setIdTypeMat(typeMaterielId);
        return typeMaterielService.updateTypeMateriel(typeMateriel);
    }
    @DeleteMapping("/{typeMaterielId}")
    public void deleteTypeMateriel(@PathVariable Long typeMaterielId){
        typeMaterielService.deleteTypeMateriel(typeMaterielId);
    }
    @GetMapping()
    public List<TypeMateriel> typeMaterielList(){
        return typeMaterielService.typeMaterielList();
    }
    @GetMapping("/{typeMaterielId}")
    public TypeMateriel getTypeMateriel(@PathVariable Long typeMaterielId){
        return typeMaterielService.findTypeMateriel(typeMaterielId);
    }
}
