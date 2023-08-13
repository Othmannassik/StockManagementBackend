package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {
}
