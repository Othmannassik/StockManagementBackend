package ma.cih.stockmanagementbackend.webs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.entities.PdfFile;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.services.interfaces.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("commandes")
@CrossOrigin
public class CommandeController {
    private CommandeService commandeService;
    private PdfFileService pdfFileService;
    @PostMapping()
    public CommandeDTO saveCommande(@RequestParam("commande") String commandeJSON,
                                    @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CommandeDTO commandeDTO = objectMapper.readValue(commandeJSON, CommandeDTO.class);
        PdfFile pdfFile = pdfFileService.saveFile(file);
        commandeDTO.setBonCmd(pdfFile);
        return commandeService.addCommande(commandeDTO);
    }
    @PutMapping("/{commandeId}")
    public CommandeDTO updateCommande(@RequestParam("commande") String commandeJSON,
                                      @RequestPart("file") MultipartFile file, @PathVariable Long commandeId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CommandeDTO commandeDTO = objectMapper.readValue(commandeJSON, CommandeDTO.class);
        commandeDTO.setIdCmd(commandeId);
        pdfFileService.updateFile(file, commandeDTO.getBonCmd().getId());
        return commandeService.updateCommande(commandeDTO);
    }
    @DeleteMapping("/{commandeId}")
    public void deleteCommande(@PathVariable Long commandeId) throws CommandeNotFoundException {
        CommandeDTO commandeDTO = commandeService.findCommande(commandeId);
        commandeService.deleteCommande(commandeId);
        pdfFileService.deleteFile(commandeDTO.getBonCmd().getId());
    }
    @GetMapping()
    public List<CommandeDTO> commandeList(){
        return commandeService.commandeList();
    }
    @GetMapping("/{commandeId}")
    public CommandeDTO getCommande(@PathVariable Long commandeId) throws CommandeNotFoundException {
        return commandeService.findCommande(commandeId);
    }
    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "Commandes.xlsx";
        InputStreamResource file = new InputStreamResource(commandeService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
