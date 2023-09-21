package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.CommandeDTO;
import ma.cih.stockmanagementbackend.dtos.LivraisonDTO;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Livraison;
import ma.cih.stockmanagementbackend.enums.StatusCmd;
import ma.cih.stockmanagementbackend.exceptions.CommandeNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.LivraisonNotFoundException;
import ma.cih.stockmanagementbackend.mappers.CommandeMapper;
import ma.cih.stockmanagementbackend.mappers.LivraisonMapper;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.repositories.LivraisonRepository;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import ma.cih.stockmanagementbackend.services.interfaces.LivraisonService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielDetailService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class LivraisonServiceImpl implements LivraisonService {
    private LivraisonRepository livraisonRepository;
    private CommandeService commandeService;
    private CommandeMapper commandeMapper;
    private LivraisonMapper livraisonMapper;
    private MaterielService materielService;
    private MaterielMapper materielMapper;
    private MaterielDetailService materielDetailService;
    @Override
    public LivraisonDTO addLivraison(LivraisonDTO livraisonDTO, Long commandeId) throws CommandeNotFoundException {
        Livraison livraison = livraisonMapper.toLivraison(livraisonDTO);
        CommandeDTO commandeDTO = commandeService.findCommande(commandeId);
        livraison.setCommande(commandeMapper.toCommande(commandeDTO));
        livraisonRepository.save(livraison);

        livraison.getCommande().getMateriel().setQuantity(livraison.getCommande().getMateriel().getQuantity() + livraisonDTO.getQuantity());
        materielService.updateMateriel(materielMapper.toMaterielDTO(livraison.getCommande().getMateriel()));

        for (int i = 0; i < livraisonDTO.getQuantity(); i++) {
            MaterielDetailDTO materielDetailDTO = new MaterielDetailDTO();
            materielDetailDTO.setMaterielDTO(commandeDTO.getMateriel());
            materielDetailService.addMaterielDetail(materielDetailDTO);
        }
        int count = commandeDTO.getLivraisonList().stream()
                .collect(Collectors.summingInt(LivraisonDTO::getQuantity))
                .intValue();
        if ((livraisonDTO.getQuantity() + count) == commandeDTO.getQuantity()){
            commandeDTO.setStatus(StatusCmd.DELIVERED);
        } else {
            commandeDTO.setStatus(StatusCmd.PENDING);
        }
        commandeService.updateCommande(commandeDTO);
        return livraisonDTO;
    }

    @Override
    public LivraisonDTO updateLivraison(LivraisonDTO livraisonDTO, Long commandeId) throws CommandeNotFoundException {
        Livraison livraison = livraisonMapper.toLivraison(livraisonDTO);
        livraison.setCommande(commandeMapper.toCommande(commandeService.findCommande(commandeId)));
        livraisonRepository.save(livraison);
        return livraisonDTO;
    }

    @Override
    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }

    @Override
    public LivraisonDTO findLivraison(Long id) throws LivraisonNotFoundException {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new LivraisonNotFoundException(String.format("Livraison with id = %s Not Found", id)));
        return livraisonMapper.toLivraisonDTO(livraison);
    }

    @Override
    public List<LivraisonDTO> livraisonList() {
        return livraisonRepository.findAll().stream()
                .map(liv -> livraisonMapper.toLivraisonDTO(liv))
                .toList();
    }
    @Override
    public CommandeDTO cmdByLivraison(Long id) throws LivraisonNotFoundException {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new LivraisonNotFoundException(String.format("Livraison with id = %s Not Found", id)));
        return commandeMapper.toCommandeDTO(livraison.getCommande());
    }
    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "N° BL", "N° BC", "Date", "Quantité"};
        String SHEET = "Commandes";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (LivraisonDTO livraisonDTO : livraisonList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(livraisonDTO.getIdLiv());
                row.createCell(1).setCellValue(livraisonDTO.getNumBonLiv());
                CommandeDTO commandeDTO = cmdByLivraison(livraisonDTO.getIdLiv());
                row.createCell(2).setCellValue(commandeDTO.getNumBonCmd());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                row.createCell(3).setCellValue(livraisonDTO.getDate().format(formatter));
                row.createCell(4).setCellValue(livraisonDTO.getQuantity());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LivraisonNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
