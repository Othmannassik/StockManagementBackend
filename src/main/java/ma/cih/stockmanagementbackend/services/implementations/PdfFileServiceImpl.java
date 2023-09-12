package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.entities.PdfFile;
import ma.cih.stockmanagementbackend.repositories.PdfFileRepository;
import ma.cih.stockmanagementbackend.services.interfaces.PdfFileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PdfFileServiceImpl implements PdfFileService {
    private PdfFileRepository pdfFileRepository;
    @Override
    public PdfFile saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PdfFile pdfFile = PdfFile.builder()
                .name(fileName)
                .data(file.getBytes()).build();

        return pdfFileRepository.save(pdfFile);
    }
    @Override
    public PdfFile getFile(String id) {
        return pdfFileRepository.findById(id).get();
    }
    @Override
    public List<PdfFile> getAllFiles() {
        return pdfFileRepository.findAll();
    }
}
