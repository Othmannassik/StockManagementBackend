package ma.cih.stockmanagementbackend.dtos;

import lombok.Data;
@Data
public class PrestataireDTO {
    private Long idPres;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
