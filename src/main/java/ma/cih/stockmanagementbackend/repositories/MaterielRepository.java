package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    int countByTypeMateriel(TypeMateriel typeMateriel);
}
