package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("prestataires")
public class PrestataireController {
    private PrestataireService prestataireService;
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
    public PrestataireDTO getPrestataire(@PathVariable Long prestataireId){
        return prestataireService.findPrestataire(prestataireId);
    }
}
