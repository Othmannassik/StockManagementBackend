package ma.cih.stockmanagementbackend.webs;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("commandes")
public class CommandeController {
    private CommandeService commandeService;
    @PostMapping()
    public CommandeDTO saveCommande(@RequestBody CommandeDTO commandeDTO){
        return commandeService.addCommande(commandeDTO);
    }
    @PutMapping("/{commandeId}")
    public CommandeDTO updateCommande(@RequestBody CommandeDTO commandeDTO, @PathVariable Long commandeId){
        commandeDTO.setIdCmd(commandeId);
        return commandeService.updateCommande(commandeDTO);
    }
    @DeleteMapping("/{commandeId}")
    public void deleteCommande(@PathVariable Long commandeId){
        commandeService.deleteCommande(commandeId);
    }
    @GetMapping()
    public List<CommandeDTO> commandeList(){
        return commandeService.commandeList();
    }
    @GetMapping("/{commandeId}")
    public CommandeDTO getCommande(@PathVariable Long commandeId){
        return commandeService.findCommande(commandeId);
    }
}
