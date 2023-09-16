package ma.cih.stockmanagementbackend.mappers;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.MaterielDetailDTO;
import ma.cih.stockmanagementbackend.entities.MaterielDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterielDetailMapper {
    private MaterielMapper materielMapper;
    public MaterielDetail toMaterielDetail(MaterielDetailDTO materielDetailDTO){
        MaterielDetail materielDetail = new MaterielDetail();
        BeanUtils.copyProperties(materielDetailDTO,materielDetail);
        materielDetail.setMateriel(materielMapper.toMateriel(materielDetailDTO.getMaterielDTO()));
        return materielDetail;
    }

    public MaterielDetailDTO toMaterielDetailDTO(MaterielDetail materielDetail){
        MaterielDetailDTO materielDetailDTO = new MaterielDetailDTO();
        BeanUtils.copyProperties(materielDetail,materielDetailDTO);
        materielDetailDTO.setMaterielDTO(materielMapper.toMaterielDTO(materielDetail.getMateriel()));
        return materielDetailDTO;
    }
}
