package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}
