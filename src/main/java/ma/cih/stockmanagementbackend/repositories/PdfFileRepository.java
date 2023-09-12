package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfFileRepository extends JpaRepository<PdfFile, String> {
}
