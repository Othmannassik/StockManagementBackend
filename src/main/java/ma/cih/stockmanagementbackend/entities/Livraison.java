package ma.cih.stockmanagementbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Livraison {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLiv;
    private String bonLiv;
    private LocalDate date;
    private int quantity;
    @ManyToOne @JoinColumn(name = "idCmd")
    private Commande commande;
}
