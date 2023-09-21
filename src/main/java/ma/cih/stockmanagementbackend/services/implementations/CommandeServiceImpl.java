package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.enums.StatusCmd;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.CommandeMapper;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.mappers.PrestataireMapper;
import ma.cih.stockmanagementbackend.repositories.CommandeRepository;
import ma.cih.stockmanagementbackend.services.interfaces.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    private CommandeMapper commandeMapper;
    private PrestataireService prestataireService;
    private PrestataireMapper prestataireMapper;
    @Override
    public CommandeDTO addCommande(CommandeDTO commandeDTO){
        Commande commande = commandeMapper.toCommande(commandeDTO);
        commande.setStatus(StatusCmd.CREATED);
        commandeRepository.save(commande);
        return commandeDTO;
    }

    @Override
    public CommandeDTO updateCommande(CommandeDTO commandeDTO) {
        Commande commande = commandeMapper.toCommande(commandeDTO);
        commandeRepository.save(commande);
        return commandeDTO;
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public CommandeDTO findCommande(Long id) throws CommandeNotFoundException {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new CommandeNotFoundException(String.format("Commande with id = %s Not Found", id)));
        return commandeMapper.toCommandeDTO(commande);
    }

    @Override
    public List<CommandeDTO> commandeList() {
        return commandeRepository.findAll().stream()
                .map(cmd -> commandeMapper.toCommandeDTO(cmd))
                .toList();
    }

    @Override
    public int nbCmdByPrestataire(Long id) throws PrestataireNotFoundException {
        PrestataireDTO prestataireDTO = prestataireService.findPrestataire(id);
        return commandeRepository.countByPrestataire(prestataireMapper.toPrestataire(prestataireDTO));
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "N° BC", "Date", "Quantité", "Status", "Matériel", "Type", "Prestataire", "Etablissement"};
        String SHEET = "Commandes";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (CommandeDTO commandeDTO : commandeList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(commandeDTO.getIdCmd());
                row.createCell(1).setCellValue(commandeDTO.getNumBonCmd());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                row.createCell(2).setCellValue(commandeDTO.getDate().format(formatter));
                row.createCell(3).setCellValue(commandeDTO.getQuantity());
                row.createCell(4).setCellValue(commandeDTO.getStatus().toString());
                row.createCell(5).setCellValue(commandeDTO.getMateriel().getModel());
                row.createCell(6).setCellValue(commandeDTO.getMateriel().getTypeMateriel().getName());
                row.createCell(7).setCellValue(commandeDTO.getPrestataire().getRaisonSocial());
                row.createCell(8).setCellValue(commandeDTO.getEtablissement().getName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
