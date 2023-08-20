package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDTO;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class MaterielServiceImpl implements MaterielService {
    private MaterielRepository materielRepository;
    private MaterielMapper materielMapper;
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
    public MaterielDTO findMateriel(Long id) {
        Materiel materiel = materielRepository.findById(id).orElse(null);
        return materielMapper.toMaterielDTO(materiel);
    }

    @Override
    public List<MaterielDTO> materielList() {
        List<Materiel> materielList = materielRepository.findAll();
        return materielList.stream()
                .map(mat -> materielMapper.toMaterielDTO(mat))
                .toList();
    }
}
