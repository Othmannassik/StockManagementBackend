package ma.cih.stockmanagementbackend.services.interfaces;

import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.MaterielNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.PrestataireNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface MaterielService {
    MaterielDTO addMateriel(MaterielDTO materielDTO);
    MaterielDTO updateMateriel(MaterielDTO materielDTO);
    void deleteMateriel(Long id);
    MaterielDTO findMateriel(Long id) throws MaterielNotFoundException;
    List<MaterielDTO> materielList();
    //int nbMatByEtablissement(Long id) throws EtablissementNotFoundException;
    int nbMatByTypeMateriel(Long id) throws TypeMaterielNotFoundException;
    ByteArrayInputStream exportExcel();
}
