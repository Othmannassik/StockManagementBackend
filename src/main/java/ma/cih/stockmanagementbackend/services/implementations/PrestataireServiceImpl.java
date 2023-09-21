package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.PrestataireMapper;
import ma.cih.stockmanagementbackend.repositories.CommandeRepository;
import ma.cih.stockmanagementbackend.repositories.PrestataireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.CommandeService;
import ma.cih.stockmanagementbackend.services.interfaces.PrestataireService;
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
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class PrestataireServiceImpl implements PrestataireService {
    private PrestataireRepository prestataireRepository;
    private PrestataireMapper prestataireMapper;
    private CommandeRepository commandeRepository;
    @Override
    public PrestataireDTO addPrestataire(PrestataireDTO prestataireDTO) {
        Prestataire prestataire = prestataireMapper.toPrestataire(prestataireDTO);
        prestataireRepository.save(prestataire);
        return prestataireDTO;
    }

    @Override
    public PrestataireDTO updatePrestataire(PrestataireDTO prestataireDTO) {
        Prestataire prestataire = prestataireMapper.toPrestataire(prestataireDTO);
        prestataireRepository.save(prestataire);
        return prestataireDTO;
    }

    @Override
    public void deletePrestataire(Long id) {
        prestataireRepository.deleteById(id);
    }

    @Override
    public PrestataireDTO findPrestataire(Long id) throws PrestataireNotFoundException {
        Prestataire prestataire = prestataireRepository.findById(id)
                .orElseThrow(() -> new PrestataireNotFoundException(String.format("Prestataire with id = %s Not Found", id)));
        return prestataireMapper.toPrestataireDTO(prestataire);
    }

    @Override
    public List<PrestataireDTO> prestataireList() {
        return prestataireRepository.findAll().stream()
                .map(pres -> prestataireMapper.toPrestataireDTO(pres))
                .toList();
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "Raison Social", "Email", "Téléphone", "Nombre de Commande"};
        String SHEET = "Prestataires";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (PrestataireDTO prestataireDTO : prestataireList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(prestataireDTO.getIdPres());
                row.createCell(1).setCellValue(prestataireDTO.getRaisonSocial());
                row.createCell(2).setCellValue(prestataireDTO.getEmail());
                row.createCell(3).setCellValue(prestataireDTO.getTelephone());
                row.createCell(4).setCellValue(commandeRepository.countByPrestataire(prestataireMapper.toPrestataire(prestataireDTO)));
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
