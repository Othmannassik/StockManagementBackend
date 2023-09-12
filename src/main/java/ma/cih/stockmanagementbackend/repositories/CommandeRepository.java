package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Commande;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    int countByPrestataire(Prestataire prestataire);
}
