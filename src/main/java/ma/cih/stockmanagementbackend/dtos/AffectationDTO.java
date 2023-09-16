package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AffectationDTO {
    private Long idAff;
    private LocalDate date;
    private String motif;
    private ProprietaireDTO proprietaireDTO;
    private MaterielDetailDTO materielDetailDTO;
}
