package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.dtos.TypeMaterielDTO;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.MaterielNotFoundException;
import ma.cih.stockmanagementbackend.exceptions.TypeMaterielNotFoundException;
import ma.cih.stockmanagementbackend.mappers.EtablissementMapper;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.mappers.TypeMaterielMapper;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielDetailService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import ma.cih.stockmanagementbackend.services.interfaces.TypeMaterielService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class MaterielServiceImpl implements MaterielService {
    private MaterielRepository materielRepository;
    private MaterielMapper materielMapper;
    private EtablissementService etablissementService;
    private EtablissementMapper etablissementMapper;
    private TypeMaterielService typeMaterielService;
    private TypeMaterielMapper typeMaterielMapper;
    private MaterielDetailService materielDetailService;
    @Override
    public MaterielDTO addMateriel(MaterielDTO materielDTO) {
        Materiel materiel =materielMapper.toMateriel(materielDTO);
        materielRepository.save(materiel);
        return materielDTO;
    }

    @Override
    public MaterielDTO updateMateriel(MaterielDTO materielDTO) {
        Materiel materiel = materielMapper.toMateriel(materielDTO);
        materielRepository.save(materiel);
        return materielDTO;
    }

    @Override
    public void deleteMateriel(Long id) {
        materielRepository.deleteById(id);
    }

    @Override
    public MaterielDTO findMateriel(Long id) throws MaterielNotFoundException {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new MaterielNotFoundException(String.format("Materiel with id = %s Not Found", id)));
        return materielMapper.toMaterielDTO(materiel);
    }

    @Override
    public List<MaterielDTO> materielList() {
        List<Materiel> materielList = materielRepository.findAll();
        return materielList.stream()
                .map(mat -> materielMapper.toMaterielDTO(mat))
                .toList();
    }

    @Override
    public int nbMatByTypeMateriel(Long id) throws TypeMaterielNotFoundException {
        TypeMaterielDTO typeMaterielDTO = typeMaterielService.findTypeMateriel(id);
        return materielRepository.countByTypeMateriel(typeMaterielMapper.toTypeMateriel(typeMaterielDTO));
    }
}
