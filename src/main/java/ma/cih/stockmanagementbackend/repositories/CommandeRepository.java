package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
