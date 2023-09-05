package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("typeMateriels")
@CrossOrigin
public class TypeMaterielController {
    private TypeMaterielService typeMaterielService;
    @PostMapping()
    public TypeMaterielDTO saveTypeMateriel(@RequestBody TypeMaterielDTO typeMaterielDTO){
        return typeMaterielService.addTypeMateriel(typeMaterielDTO);
    }
    @PutMapping("/{typeMaterielId}")
    public TypeMaterielDTO updateTypeMateriel(@RequestBody TypeMaterielDTO typeMaterielDTO, @PathVariable Long typeMaterielId){
        typeMaterielDTO.setIdTypeMat(typeMaterielId);
        return typeMaterielService.updateTypeMateriel(typeMaterielDTO);
    }
    @DeleteMapping("/{typeMaterielId}")
    public void deleteTypeMateriel(@PathVariable Long typeMaterielId){
        typeMaterielService.deleteTypeMateriel(typeMaterielId);
    }
    @GetMapping()
    public List<TypeMaterielDTO> typeMaterielList(){
        return typeMaterielService.typeMaterielList();
    }
    @GetMapping("/{typeMaterielId}")
    public TypeMaterielDTO getTypeMateriel(@PathVariable Long typeMaterielId) throws TypeMaterielNotFoundException {
        return typeMaterielService.findTypeMateriel(typeMaterielId);
    }
}
