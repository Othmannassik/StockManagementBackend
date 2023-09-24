package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.exceptions.MaterielDetailNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("materiel-details")
@CrossOrigin
public class MaterielDetailController {
    private MaterielDetailService materielDetailService;
    @PostMapping()
    public MaterielDetailDTO saveMaterielDetail(@RequestBody MaterielDetailDTO materielDetailDTO){
        return materielDetailService.addMaterielDetail(materielDetailDTO);
    }
    @PutMapping("/{materielDetailId}")
    public MaterielDetailDTO updateMaterielDetail(@RequestBody MaterielDetailDTO materielDetailDTO, @PathVariable Long materielDetailId){
        materielDetailDTO.setIdMatDet(materielDetailId);
        return materielDetailService.updateMaterielDetail(materielDetailDTO);
    }
    @DeleteMapping("/{materielDetailId}")
    public void deleteMaterielDetail(@PathVariable Long materielDetailId) throws MaterielDetailNotFoundException {
        materielDetailService.deleteMaterielDetail(materielDetailId);
    }
    @GetMapping()
    public List<MaterielDetailDTO> materielDetailList(){
        return materielDetailService.materielDetailList();
    }
    @GetMapping("/{materielDetailId}")
    public MaterielDetailDTO getMaterielDetail(@PathVariable Long materielDetailId) throws MaterielDetailNotFoundException {
        return materielDetailService.findMaterielDetail(materielDetailId);
    }
    @GetMapping("/{materielDetailId}/materiels")
    public List<MaterielDetailDTO> getMaterielDetailsByMat(@PathVariable Long materielDetailId) {
        return materielDetailService.materielDetailListByMat(materielDetailId);
    }
    @GetMapping("/count")
    public Long countMateriles() {
        return materielDetailService.countMateriels();
    }

    @GetMapping("/notUsedMat")
    public Long countMaterielsNotUsed() {
        return materielDetailService.countMaterielsNotUsed();
    }
}
