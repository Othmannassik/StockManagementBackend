package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Affectation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAff;
    private LocalDate date;
    private String motif;
    @ManyToOne @JoinColumn(name = "idProp")
    private Proprietaire proprietaire;
    @ManyToOne @JoinColumn(name = "idMat")
    private Materiel materiel;
}
