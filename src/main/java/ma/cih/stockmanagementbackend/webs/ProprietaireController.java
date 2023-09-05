package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("proprietaires")
@CrossOrigin
public class ProprietaireController {
    private ProprietaireService proprietaireService;
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
}
