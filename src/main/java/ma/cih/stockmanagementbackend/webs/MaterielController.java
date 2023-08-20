package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("materiels")
public class MaterielController {
    private MaterielService materielService;
    @PostMapping()
    public MaterielDTO saveMateriel(@RequestBody MaterielDTO materielDTO){
        return materielService.addMateriel(materielDTO);
    }
    @PutMapping("/{materielId}")
    public MaterielDTO updateMateriel(@RequestBody MaterielDTO materielDTO, @PathVariable Long materielId){
        materielDTO.setIdMat(materielId);
        return materielService.updateMateriel(materielDTO);
    }
    @DeleteMapping("/{materielId}")
    public void deleteMateriel(@PathVariable Long materielId){
        materielService.deleteMateriel(materielId);
    }
    @GetMapping()
    public List<MaterielDTO> materielList(){
        return materielService.materielList();
    }
    @GetMapping("/{materielId}")
    public MaterielDTO getMateriel(@PathVariable Long materielId){
        return materielService.findMateriel(materielId);
    }
}
