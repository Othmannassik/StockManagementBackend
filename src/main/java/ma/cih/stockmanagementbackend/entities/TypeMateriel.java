package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TypeMateriel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeMat;
    private String name;
    @OneToMany(mappedBy = "typeMateriel")
    private List<Materiel> materielList;
}
