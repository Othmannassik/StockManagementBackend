package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;
@Data
public class ProprietaireDTO {
    private Long idProp;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
