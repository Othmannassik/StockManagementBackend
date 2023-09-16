package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.entities.Materiel;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import ma.cih.stockmanagementbackend.exceptions.MaterielDetailNotFoundException;
import ma.cih.stockmanagementbackend.mappers.MaterielDetailMapper;
import ma.cih.stockmanagementbackend.mappers.MaterielMapper;
import ma.cih.stockmanagementbackend.repositories.MaterielDetailRepository;
import ma.cih.stockmanagementbackend.repositories.MaterielRepository;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielDetailService;
import ma.cih.stockmanagementbackend.services.interfaces.MaterielService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class MaterielDetailServiceImpl implements MaterielDetailService {
    private MaterielDetailRepository materielDetailRepository;
    private MaterielDetailMapper materielDetailMapper;
    private MaterielRepository materielRepository;
    private MaterielMapper materielMapper;
    @Override
    public MaterielDetailDTO addMaterielDetail(MaterielDetailDTO materielDetailDTO) {
        MaterielDetail materielDetail = materielDetailMapper.toMaterielDetail(materielDetailDTO);
        materielDetailRepository.save(materielDetail);
        return materielDetailDTO;
    }

    @Override
    public MaterielDetailDTO updateMaterielDetail(MaterielDetailDTO materielDetailDTO) {
        MaterielDetail materielDetail = materielDetailMapper.toMaterielDetail(materielDetailDTO);
        materielDetailRepository.save(materielDetail);
        return materielDetailDTO;
    }

    @Override
    public void deleteMaterielDetail(Long id) throws MaterielDetailNotFoundException {
        MaterielDetailDTO materielDetailDTO = findMaterielDetail(id);
        materielDetailDTO.getMaterielDTO().setQuantity(materielDetailDTO.getMaterielDTO().getQuantity() - 1);
        materielRepository.save(materielMapper.toMateriel(materielDetailDTO.getMaterielDTO()));
        materielDetailRepository.deleteById(id);
    }

    @Override
    public MaterielDetailDTO findMaterielDetail(Long id) throws MaterielDetailNotFoundException {
        MaterielDetail materielDetail = materielDetailRepository.findById(id)
                .orElseThrow(() -> new MaterielDetailNotFoundException(String.format("MaterielDetail with id = %s Not Found", id)));
        return materielDetailMapper.toMaterielDetailDTO(materielDetail);
    }

    @Override
    public List<MaterielDetailDTO> materielDetailList() {
        List<MaterielDetail> materielDetailList = materielDetailRepository.findAll();
        return materielDetailList.stream()
                .map(matDet -> materielDetailMapper.toMaterielDetailDTO(matDet))
                .toList();
    }

    @Override
    public List<MaterielDetailDTO> materielDetailListByMat(Long id){
        Materiel materiel = materielRepository.findById(id).get();
        return materielDetailRepository.findByMateriel(materiel).stream()
                .map(matDet -> materielDetailMapper.toMaterielDetailDTO(matDet))
                .toList();
    }
}
