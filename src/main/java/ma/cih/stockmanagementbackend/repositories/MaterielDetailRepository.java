package ma.cih.stockmanagementbackend.repositories;

import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterielDetailRepository extends JpaRepository<MaterielDetail, Long> {
    List<MaterielDetail> findByMateriel(Materiel materiel);
    @Query("SELECT COUNT(m) FROM MaterielDetail m LEFT JOIN Affectation a ON m.idMatDet = a.materielDetail.idMatDet WHERE a.idAff IS NULL")
    Long countMaterielsNotUsed();
}
