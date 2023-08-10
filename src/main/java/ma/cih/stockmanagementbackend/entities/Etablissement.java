package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Etablissement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtb;
    private String name;
    private String city;
    @OneToMany(mappedBy = "etablissement")
    private List<Materiel> materielList;
}
