package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Materiel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMat;
    private String model;
    //private int quantity;
    private Long numSerie;
    private String inventaireCih;
    @ManyToOne @JoinColumn(name="idTypeMat")
    private TypeMateriel typeMateriel;
    @ManyToOne @JoinColumn(name="idEtb")
    private Etablissement etablissement;
    @OneToMany(mappedBy = "materiel")
    private List<Affectation> propList;
    @OneToMany(mappedBy = "materiel")
    private List<Commande> commandeList;
}
