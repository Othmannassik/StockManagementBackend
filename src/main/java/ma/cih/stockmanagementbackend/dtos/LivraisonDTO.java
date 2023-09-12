package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LivraisonDTO {
    private Long idLiv;
    private String bonLiv;
    private LocalDate date;
    private int quantity;
}
