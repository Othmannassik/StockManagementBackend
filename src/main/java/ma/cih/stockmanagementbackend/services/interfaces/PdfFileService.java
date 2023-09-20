package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.entities.PdfFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PdfFileService {
    PdfFile saveFile(MultipartFile file) throws IOException;
    PdfFile getFile(String id);
    List<PdfFile> getAllFiles();
    void deleteFile(String id);
    PdfFile updateFile(MultipartFile file, String id) throws IOException;
}
