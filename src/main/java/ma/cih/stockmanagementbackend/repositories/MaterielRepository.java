package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
}
