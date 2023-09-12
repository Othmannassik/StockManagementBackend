package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Etablissement;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.Prestataire;
import ma.cih.stockmanagementbackend.entities.TypeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    int countByEtablissement(Etablissement etablissement);
    int countByTypeMateriel(TypeMateriel typeMateriel);
}
