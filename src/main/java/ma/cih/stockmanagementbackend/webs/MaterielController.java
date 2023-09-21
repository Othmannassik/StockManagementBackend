package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.exceptions.MaterielNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("materiels")
@CrossOrigin
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
    public MaterielDTO getMateriel(@PathVariable Long materielId) throws MaterielNotFoundException {
        return materielService.findMateriel(materielId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Matériels.xlsx";
        InputStreamResource file = new InputStreamResource(materielService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
