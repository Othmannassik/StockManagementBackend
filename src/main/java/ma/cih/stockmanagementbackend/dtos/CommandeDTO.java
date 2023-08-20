package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;
import ma.cih.stockmanagementbackend.enums.StatusCmd;

import java.time.LocalDate;

@Data
public class CommandeDTO {
    private Long idCmd;
    private String bonCmd;
    private LocalDate date;
    private int quantity;
    private StatusCmd status;
    private MaterielDTO materielDTO;
    private PrestataireDTO prestataireDTO;
}
