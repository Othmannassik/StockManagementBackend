package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
}
