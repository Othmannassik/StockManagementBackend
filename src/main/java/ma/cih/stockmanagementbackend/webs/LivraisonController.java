package ma.cih.stockmanagementbackend.webs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.entities.PdfFile;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import ma.cih.stockmanagementbackend.services.interfaces.PdfFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("livraisons")
@CrossOrigin
public class LivraisonController {
    private LivraisonService livraisonService;
    private PdfFileService pdfFileService;
    @PostMapping("/{commandeId}")
    public LivraisonDTO saveLivraison(@RequestParam("livraison") String livraisonJSON,
                                      @RequestPart("file") MultipartFile file, @PathVariable Long commandeId) throws CommandeNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        LivraisonDTO livraisonDTO = objectMapper.readValue(livraisonJSON, LivraisonDTO.class);
        PdfFile pdfFile = pdfFileService.saveFile(file);
        livraisonDTO.setBonLiv(pdfFile);
        return livraisonService.addLivraison(livraisonDTO, commandeId);
    }
    @PutMapping("/{livraisonId}/{commandeId}")
    public LivraisonDTO updateLivraison(@RequestParam("livraison") String livraisonJSON,
                                        @RequestPart("file") MultipartFile file, @PathVariable Long livraisonId, @PathVariable Long commandeId) throws CommandeNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        LivraisonDTO livraisonDTO = objectMapper.readValue(livraisonJSON, LivraisonDTO.class);
        livraisonDTO.setIdLiv(livraisonId);
        pdfFileService.updateFile(file, livraisonDTO.getBonLiv().getId());
        return livraisonService.updateLivraison(livraisonDTO, commandeId);
    }
    @DeleteMapping("/{livraisonId}")
    public void deleteLivraison(@PathVariable Long livraisonId) throws LivraisonNotFoundException {
        LivraisonDTO livraisonDTO = livraisonService.findLivraison(livraisonId);
        livraisonService.deleteLivraison(livraisonId);
        pdfFileService.deleteFile(livraisonDTO.getBonLiv().getId());
    }
    @GetMapping()
    public List<LivraisonDTO> livraisonList(){
        return livraisonService.livraisonList();
    }
    @GetMapping("/{livraisonId}")
    public LivraisonDTO getLivraison(@PathVariable Long livraisonId) throws LivraisonNotFoundException {
        return livraisonService.findLivraison(livraisonId);
    }
    @GetMapping("/{livraisonId}/commande")
    public CommandeDTO cmdByLivraison(@PathVariable Long livraisonId) throws LivraisonNotFoundException {
        return livraisonService.cmdByLivraison(livraisonId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Livraisons.xlsx";
        InputStreamResource file = new InputStreamResource(livraisonService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
