package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Affectation;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import ma.cih.stockmanagementbackend.entities.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {
    List<Affectation> findByProprietaire(Proprietaire proprietaire);
    int countByMaterielDetail(MaterielDetail materielDetail);
}
