package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cih.stockmanagementbackend.enums.StatusCmd;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCmd;
    private String numBonCmd;
    private LocalDate date;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private StatusCmd status;
    @OneToOne
    private PdfFile bonCmd;
    @ManyToOne @JoinColumn(name="idMat")
    private Materiel materiel;
    @ManyToOne @JoinColumn(name="idEtb")
    private Etablissement etablissement;
    @ManyToOne @JoinColumn(name="idPres")
    private Prestataire prestataire;
    @OneToMany(mappedBy = "commande")
    private List<Livraison> livraisonList;
}
