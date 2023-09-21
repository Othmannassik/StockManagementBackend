package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("typeMateriels")
@CrossOrigin
public class TypeMaterielController {
    private TypeMaterielService typeMaterielService;
    private MaterielService materielService;
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
    @GetMapping("/{typeMaterielId}/materiels")
    public int nbMaterielByTypeMateriel(@PathVariable Long typeMaterielId) throws TypeMaterielNotFoundException {
        return materielService.nbMatByTypeMateriel(typeMaterielId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "TypeMateriels.xlsx";
        InputStreamResource file = new InputStreamResource(typeMaterielService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
