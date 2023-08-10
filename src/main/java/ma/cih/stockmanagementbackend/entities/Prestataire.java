package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
public class Prestataire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPres;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "prestataire")
    private List<Commande> commandeList;
}
