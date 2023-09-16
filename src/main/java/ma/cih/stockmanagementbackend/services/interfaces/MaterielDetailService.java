package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.exceptions.MaterielDetailNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.MaterielNotFoundException;

import java.util.List;

public interface MaterielDetailService {
    MaterielDetailDTO addMaterielDetail(MaterielDetailDTO materielDetailDTO);
    MaterielDetailDTO updateMaterielDetail(MaterielDetailDTO materielDetailDTO);
    void deleteMaterielDetail(Long id) throws MaterielDetailNotFoundException;
    MaterielDetailDTO findMaterielDetail(Long id) throws MaterielDetailNotFoundException;
    List<MaterielDetailDTO> materielDetailList();
    List<MaterielDetailDTO> materielDetailListByMat(Long id);
}
