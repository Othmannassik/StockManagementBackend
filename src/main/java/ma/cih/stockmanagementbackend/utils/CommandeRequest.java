package ma.cih.stockmanagementbackend.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class CommandeRequest {
    private LocalDate date;
    private String materiel;
    private String numBonCmd;
    private int quantity;
    private Long prestataireId;
    private Long etablissementId;
    private Long typeMaterielId;
}
