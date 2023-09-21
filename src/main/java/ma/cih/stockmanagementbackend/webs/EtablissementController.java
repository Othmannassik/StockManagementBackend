package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
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
@RequestMapping("etablissements")
@CrossOrigin
public class EtablissementController {
    private EtablissementService etablissementService;
    private MaterielService materielService;
    @PostMapping()
    public EtablissementDTO saveEtablissement(@RequestBody EtablissementDTO etablissementDTO){
        return etablissementService.addEtablissement(etablissementDTO);
    }
    @PutMapping("/{etablissementId}")
    public EtablissementDTO updateEtablissement(@RequestBody EtablissementDTO etablissementDTO, @PathVariable Long etablissementId){
        etablissementDTO.setIdEtb(etablissementId);
        return etablissementService.updateEtablissement(etablissementDTO);
    }
    @DeleteMapping("/{etablissementId}")
    public void deleteEtablissement(@PathVariable Long etablissementId){
        etablissementService.deleteEtablissement(etablissementId);
    }
    @GetMapping()
    public List<EtablissementDTO> etablissementList(){
        return etablissementService.etablissementList();
    }
    @GetMapping("/{etablissementId}")
    public EtablissementDTO getEtablissement(@PathVariable Long etablissementId) throws EtablissementNotFoundException {
        return etablissementService.findEtablissement(etablissementId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Etablissements.xlsx";
        InputStreamResource file = new InputStreamResource(etablissementService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
