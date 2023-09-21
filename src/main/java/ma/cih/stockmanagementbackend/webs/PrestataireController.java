package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("prestataires")
@CrossOrigin
public class PrestataireController {
    private PrestataireService prestataireService;
    private CommandeService commandeService;
    @PostMapping()
    public PrestataireDTO savePrestataire(@RequestBody PrestataireDTO prestataireDTO){
        return prestataireService.addPrestataire(prestataireDTO);
    }
    @PutMapping("/{prestataireId}")
    public PrestataireDTO updatePrestataire(@RequestBody PrestataireDTO prestataireDTO, @PathVariable Long prestataireId){
        prestataireDTO.setIdPres(prestataireId);
        return prestataireService.updatePrestataire(prestataireDTO);
    }
    @DeleteMapping("/{prestataireId}")
    public void deletePrestataire(@PathVariable Long prestataireId){
        prestataireService.deletePrestataire(prestataireId);
    }
    @GetMapping()
    public List<PrestataireDTO> prestataireList(){
        return prestataireService.prestataireList();
    }
    @GetMapping("/{prestataireId}")
    public PrestataireDTO getPrestataire(@PathVariable Long prestataireId) throws PrestataireNotFoundException {
        return prestataireService.findPrestataire(prestataireId);
    }
    @GetMapping("/{prestataireId}/commandes")
    public int nbCmdByPrestataire(@PathVariable Long prestataireId) throws PrestataireNotFoundException {
        return commandeService.nbCmdByPrestataire(prestataireId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Prestataires.xlsx";
        InputStreamResource file = new InputStreamResource(prestataireService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
