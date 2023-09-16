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
    private int quantity;
    @ManyToOne @JoinColumn(name="idTypeMat")
    private TypeMateriel typeMateriel;
    @OneToMany(mappedBy = "materiel")
    private List<Commande> commandeList;
    @OneToMany(mappedBy = "materiel")
    private List<MaterielDetail> materielDetailList;
}
