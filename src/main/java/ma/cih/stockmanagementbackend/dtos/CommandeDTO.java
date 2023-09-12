package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;
import ma.cih.stockmanagementbackend.entities.PdfFile;
import ma.cih.stockmanagementbackend.enums.StatusCmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandeDTO {
    private Long idCmd;
    private String numBonCmd;
    private LocalDate date;
    private int quantity;
    private StatusCmd status;
    private PdfFile bonCmd;
    private MaterielDTO materiel;
    private PrestataireDTO prestataire;
    private List<LivraisonDTO> livraisonList = new ArrayList<>();
}
