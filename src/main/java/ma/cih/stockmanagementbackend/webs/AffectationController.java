package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.AffectationDTO;
import ma.cih.stockmanagementbackend.exceptions.AffectationNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.AffectationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("affectations")
@CrossOrigin
public class AffectationController {
    private AffectationService affectationService;
    @PostMapping()
    public AffectationDTO saveAffectation(@RequestBody AffectationDTO affectationDTO){
        return affectationService.addAffectation(affectationDTO);
    }
    @PutMapping("/{affectationId}")
    public AffectationDTO updateAffectation(@RequestBody AffectationDTO affectationDTO, @PathVariable Long affectationId){
        affectationDTO.setIdAff(affectationId);
        return affectationService.updateAffectation(affectationDTO);
    }
    @DeleteMapping("/{affectationId}")
    public void deleteAffectation(@PathVariable Long affectationId){
        affectationService.deleteAffectation(affectationId);
    }
    @GetMapping()
    public List<AffectationDTO> affectationList(){
        return affectationService.affectationList();
    }
    @GetMapping("/{affectationId}")
    public AffectationDTO getAffectation(@PathVariable Long affectationId) throws AffectationNotFoundException {
        return affectationService.findAffectation(affectationId);
    }
}
