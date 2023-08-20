package ma.cih.stockmanagementbackend.services.implementations;

import lombok.AllArgsConstructor;
import ma.cih.stockmanagementbackend.dtos.EtablissementDTO;
import ma.cih.stockmanagementbackend.entities.Etablissement;
import ma.cih.stockmanagementbackend.exceptions.EtablissementNotFoundException;
import ma.cih.stockmanagementbackend.mappers.EtablissementMapper;
import ma.cih.stockmanagementbackend.repositories.EtablissementRepository;
import ma.cih.stockmanagementbackend.services.interfaces.EtablissementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class EtablissementServiceImpl implements EtablissementService {
    private EtablissementRepository etablissementRepository;
    private EtablissementMapper etablissementMapper;
    @Override
    public EtablissementDTO addEtablissement(EtablissementDTO etablissementDTO) {
        Etablissement etablissement = etablissementMapper.toEtablissement(etablissementDTO);
        etablissementRepository.save(etablissement);
        return etablissementDTO;
    }

    @Override
    public EtablissementDTO updateEtablissement(EtablissementDTO etablissementDTO) {
        Etablissement etablissement = etablissementMapper.toEtablissement(etablissementDTO);
        etablissementRepository.save(etablissement);
        return etablissementDTO;
    }

    @Override
    public void deleteEtablissement(Long id) {
        etablissementRepository.deleteById(id);
    }

    @Override
    public EtablissementDTO findEtablissement(Long id) throws EtablissementNotFoundException {
        Etablissement etablissement = etablissementRepository.findById(id)
                .orElseThrow(() -> new EtablissementNotFoundException(String.format("Etablissement with id = %s Not Found", id)));
        return etablissementMapper.toEtablissementDTO(etablissement);
    }

    @Override
    public List<EtablissementDTO> etablissementList() {
        List<Etablissement> etablissementList = etablissementRepository.findAll();
        return etablissementList.stream()
                .map(etb -> etablissementMapper.toEtablissementDTO(etb))
                .toList();
    }
}
