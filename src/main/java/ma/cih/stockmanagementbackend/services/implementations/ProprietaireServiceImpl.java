package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.dtos.ProprietaireDTO;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.exceptions.ProprietaireNotFoundException;
import ma.cih.stockmanagementbackend.mappers.ProprietaireMapper;
import ma.cih.stockmanagementbackend.repositories.ProprietaireRepository;
import ma.cih.stockmanagementbackend.services.interfaces.ProprietaireService;
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
public class ProprietaireServiceImpl implements ProprietaireService {
    private ProprietaireRepository proprietaireRepository;
    private ProprietaireMapper proprietaireMapper;
    @Override
    public ProprietaireDTO addProprietaire(ProprietaireDTO proprietaireDTO) {
        Proprietaire proprietaire = proprietaireMapper.toProprietaire(proprietaireDTO);
        proprietaireRepository.save(proprietaire);
        return proprietaireDTO;
    }

    @Override
    public ProprietaireDTO updateProprietaire(ProprietaireDTO proprietaireDTO) {
        Proprietaire proprietaire = proprietaireMapper.toProprietaire(proprietaireDTO);
        proprietaireRepository.save(proprietaire);
        return proprietaireDTO;
    }

    @Override
    public void deleteProprietaire(Long id) {
        proprietaireRepository.deleteById(id);
    }

    @Override
    public ProprietaireDTO findProprietaire(Long id) throws ProprietaireNotFoundException {
        Proprietaire proprietaire = proprietaireRepository.findById(id)
                .orElseThrow(() -> new ProprietaireNotFoundException(String.format("Proprietaire with id = %s Not Found", id)));
        return proprietaireMapper.toProprietaireDTO(proprietaire);
    }

    @Override
    public List<ProprietaireDTO> proprietaireList() {
        return proprietaireRepository.findAll().stream()
                .map(prop -> proprietaireMapper.toProprietaireDTO(prop))
                .toList();
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "Prénom", "Nom", "Email", "Téléphone"};
        String SHEET = "Proprietaires";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ProprietaireDTO proprietaireDTO : proprietaireList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(proprietaireDTO.getIdProp());
                row.createCell(1).setCellValue(proprietaireDTO.getFirstName());
                row.createCell(2).setCellValue(proprietaireDTO.getLastName());
                row.createCell(3).setCellValue(proprietaireDTO.getEmail());
                row.createCell(4).setCellValue(proprietaireDTO.getTelephone());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
