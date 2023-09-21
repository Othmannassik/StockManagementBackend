package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.exceptions.MaterielNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.mappers.TypeMaterielMapper;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
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
public class MaterielServiceImpl implements MaterielService {
    private MaterielRepository materielRepository;
    private MaterielMapper materielMapper;
    private TypeMaterielService typeMaterielService;
    private TypeMaterielMapper typeMaterielMapper;
    @Override
    public MaterielDTO addMateriel(MaterielDTO materielDTO) {
        Materiel materiel =materielMapper.toMateriel(materielDTO);
        materielRepository.save(materiel);
        return materielDTO;
    }

    @Override
    public MaterielDTO updateMateriel(MaterielDTO materielDTO) {
        Materiel materiel = materielMapper.toMateriel(materielDTO);
        materielRepository.save(materiel);
        return materielDTO;
    }

    @Override
    public void deleteMateriel(Long id) {
        materielRepository.deleteById(id);
    }

    @Override
    public MaterielDTO findMateriel(Long id) throws MaterielNotFoundException {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new MaterielNotFoundException(String.format("Materiel with id = %s Not Found", id)));
        return materielMapper.toMaterielDTO(materiel);
    }

    @Override
    public List<MaterielDTO> materielList() {
        List<Materiel> materielList = materielRepository.findAll();
        return materielList.stream()
                .map(mat -> materielMapper.toMaterielDTO(mat))
                .toList();
    }

    @Override
    public int nbMatByTypeMateriel(Long id) throws TypeMaterielNotFoundException {
        TypeMaterielDTO typeMaterielDTO = typeMaterielService.findTypeMateriel(id);
        return materielRepository.countByTypeMateriel(typeMaterielMapper.toTypeMateriel(typeMaterielDTO));
    }

    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "Modèle", "Quantité", "Type"};
        String SHEET = "Matériels";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (MaterielDTO materielDTO : materielList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(materielDTO.getIdMat());
                row.createCell(1).setCellValue(materielDTO.getModel());
                row.createCell(2).setCellValue(materielDTO.getQuantity());
                row.createCell(3).setCellValue(materielDTO.getTypeMateriel().getName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
