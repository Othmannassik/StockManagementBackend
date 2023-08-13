package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietaireRepository extends JpaRepository<Proprietaire, Long> {
}
