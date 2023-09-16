package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MaterielDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatDet;
    private String numSerie;
    private String inventaireCih;
    private int usageCount;
    @ManyToOne @JoinColumn(name="idMat")
    private Materiel materiel;
    @OneToMany(mappedBy = "materielDetail")
    private List<Affectation> propList;
}
