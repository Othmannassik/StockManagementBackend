package ma.cih.stockmanagementbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ma.cih.stockmanagementbackend.entities.PdfFile;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LivraisonDTO {
    private Long idLiv;
    private String numBonLiv;
    private LocalDate date;
    private int quantity;
    private PdfFile bonLiv;
}
