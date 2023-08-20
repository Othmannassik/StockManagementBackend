package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.MaterielDTO;

import java.util.List;

public interface MaterielService {
    MaterielDTO addMateriel(MaterielDTO materielDTO);
    MaterielDTO updateMateriel(MaterielDTO materielDTO);
    void deleteMateriel(Long id);
    MaterielDTO findMateriel(Long id);
    List<MaterielDTO> materielList();
}
