package filev.example.diplomirane.services;

import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;

import java.util.List;

public interface DiplomnaZashtitaService {
    DiplomnaZashtitaEntity save(DiplomnaZashtitaEntity diplomnaZashtitaEntity);

    List<DiplomnaZashtitaEntity> findAll();

    void delete(Long id);
}
