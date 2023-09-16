package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;


@Data
public class MaterielDTO {
    private Long idMat;
    private String model;
    private int quantity;
    private TypeMaterielDTO typeMateriel;
}
