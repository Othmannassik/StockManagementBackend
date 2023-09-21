package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.dtos.PrestataireDTO;
import ma.cih.stockmanagementbackend.entities.Etablissement;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.mappers.EtablissementMapper;
import ma.cih.stockmanagementbackend.repositories.EtablissementRepository;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
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
public class EtablissementServiceImpl implements EtablissementService {
    private EtablissementRepository etablissementRepository;
    private EtablissementMapper etablissementMapper;
    @Override
    public EtablissementDTO addEtablissement(EtablissementDTO etablissementDTO) {
        Etablissement etablissement = etablissementMapper.toEtablissement(etablissementDTO);
        etablissementRepository.save(etablissement);
        return etablissementDTO;
    }

    @Override
    public EtablissementDTO updateEtablissement(EtablissementDTO etablissementDTO) {
        Etablissement etablissement = etablissementMapper.toEtablissement(etablissementDTO);
        etablissementRepository.save(etablissement);
        return etablissementDTO;
    }

    @Override
    public void deleteEtablissement(Long id) {
        etablissementRepository.deleteById(id);
    }

    @Override
    public EtablissementDTO findEtablissement(Long id) throws EtablissementNotFoundException {
        Etablissement etablissement = etablissementRepository.findById(id)
                .orElseThrow(() -> new EtablissementNotFoundException(String.format("Etablissement with id = %s Not Found", id)));
        return etablissementMapper.toEtablissementDTO(etablissement);
    }

    @Override
    public List<EtablissementDTO> etablissementList() {
        List<Etablissement> etablissementList = etablissementRepository.findAll();
        return etablissementList.stream()
                .map(etb -> etablissementMapper.toEtablissementDTO(etb))
                .toList();
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "Name", "Adresse", "Ville"};
        String SHEET = "Etablissements";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (EtablissementDTO etablissementDTO : etablissementList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(etablissementDTO.getIdEtb());
                row.createCell(1).setCellValue(etablissementDTO.getName());
                row.createCell(2).setCellValue(etablissementDTO.getAdresse());
                row.createCell(3).setCellValue(etablissementDTO.getCity());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
