package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;

@Data
public class MaterielDetailDTO {
    private Long idMatDet;
    private String numSerie;
    private String inventaireCih;
    private MaterielDTO materielDTO;
    private int usageCount;
}
