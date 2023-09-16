package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterielDetailRepository extends JpaRepository<MaterielDetail, Long> {
    List<MaterielDetail> findByMateriel(Materiel materiel);
}
