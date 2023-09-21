package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.AffectationService;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("proprietaires")
@CrossOrigin
public class ProprietaireController {
    private ProprietaireService proprietaireService;
    private AffectationService affectationService;
    @PostMapping()
    public ProprietaireDTO saveProprietaire(@RequestBody ProprietaireDTO proprietaireDTO){
        return proprietaireService.addProprietaire(proprietaireDTO);
    }
    @PutMapping("/{proprietaireId}")
    public ProprietaireDTO updateProprietaire(@RequestBody ProprietaireDTO proprietaireDTO, @PathVariable Long proprietaireId){
        proprietaireDTO.setIdProp(proprietaireId);
        return proprietaireService.updateProprietaire(proprietaireDTO);
    }
    @DeleteMapping("/{proprietaireId}")
    public void deleteProprietaire(@PathVariable Long proprietaireId){
        proprietaireService.deleteProprietaire(proprietaireId);
    }
    @GetMapping()
    public List<ProprietaireDTO> proprietaireList(){
        return proprietaireService.proprietaireList();
    }
    @GetMapping("/{proprietaireId}")
    public ProprietaireDTO getProprietaire(@PathVariable Long proprietaireId) throws ProprietaireNotFoundException {
        return proprietaireService.findProprietaire(proprietaireId);
    }
    @GetMapping("/{proprietaireId}/affectations")
    public List<AffectationDTO> affectationByProprietaire(@PathVariable Long proprietaireId) throws ProprietaireNotFoundException {
        return affectationService.affectationByProprieaire(proprietaireId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Proprietaires.xlsx";
        InputStreamResource file = new InputStreamResource(proprietaireService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
