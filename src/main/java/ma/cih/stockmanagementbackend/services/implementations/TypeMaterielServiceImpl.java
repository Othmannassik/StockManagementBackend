package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.mappers.TypeMaterielMapper;
import ma.cih.stockmanagementbackend.repositories.TypeMaterielRepository;
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
public class TypeMaterielServiceImpl implements TypeMaterielService {
    private TypeMaterielRepository typeMaterielRepository;
    private TypeMaterielMapper typeMaterielMapper;
    @Override
    public TypeMaterielDTO addTypeMateriel(TypeMaterielDTO typeMaterielDTO) {
        TypeMateriel typeMateriel = typeMaterielMapper.toTypeMateriel(typeMaterielDTO);
        typeMaterielRepository.save(typeMateriel);
        return typeMaterielDTO;
    }

    @Override
    public TypeMaterielDTO updateTypeMateriel(TypeMaterielDTO typeMaterielDTO) {
        TypeMateriel typeMateriel = typeMaterielMapper.toTypeMateriel(typeMaterielDTO);
        typeMaterielRepository.save(typeMateriel);
        return typeMaterielDTO;
    }

    @Override
    public void deleteTypeMateriel(Long id) {
        typeMaterielRepository.deleteById(id);
    }

    @Override
    public TypeMaterielDTO findTypeMateriel(Long id) throws TypeMaterielNotFoundException {
        TypeMateriel typeMateriel = typeMaterielRepository.findById(id)
                .orElseThrow(() -> new TypeMaterielNotFoundException(String.format("TypeMateriel with id = %s Not Found", id)));
        return typeMaterielMapper.toTypeMaterielDTO(typeMateriel);
    }

    @Override
    public List<TypeMaterielDTO> typeMaterielList() {
        List<TypeMateriel> typeMaterielList = typeMaterielRepository.findAll();
        return typeMaterielList.stream()
                .map(type -> typeMaterielMapper.toTypeMaterielDTO(type))
                .toList();
    }
    @Override
    public ByteArrayInputStream exportExcel() {
        String[] HEADERs = { "Id", "Name"};
        String SHEET = "TypeMateriels";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (TypeMaterielDTO typeMaterielDTO : typeMaterielList()) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(typeMaterielDTO.getIdTypeMat());
                row.createCell(1).setCellValue(typeMaterielDTO.getName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
