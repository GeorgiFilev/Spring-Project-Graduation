package filev.example.diplomirane.services.impl;

import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import filev.example.diplomirane.repositories.DiplomnaZashtitaRepository;
import filev.example.diplomirane.services.DiplomnaZashtitaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DiplomnaZashtitaServiceImpl implements DiplomnaZashtitaService {

    private DiplomnaZashtitaRepository diplomnaZashtitaRepository;

    public DiplomnaZashtitaServiceImpl(DiplomnaZashtitaRepository diplomnaZashtitaRepository) {
        this.diplomnaZashtitaRepository = diplomnaZashtitaRepository;
    }

    @Override
    public DiplomnaZashtitaEntity save(DiplomnaZashtitaEntity diplomnaZashtitaEntity) {
        return diplomnaZashtitaRepository.save(diplomnaZashtitaEntity);
    }

    @Override
    public List<DiplomnaZashtitaEntity> findAll() {
        return StreamSupport.stream(diplomnaZashtitaRepository
                .findAll()
                .spliterator(),false)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
         diplomnaZashtitaRepository.deleteById(id);
    }
}
