package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Prestataire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPres;
    private String raisonSocial;
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "prestataire")
    private List<Commande> commandeList;
}
