package filev.example.diplomirane.services;

import filev.example.diplomirane.entities.models.ZadanieEntity;

import java.util.List;
import java.util.Optional;

public interface ZadanieService {


    ZadanieEntity save(ZadanieEntity zadanieEntity);

    List<ZadanieEntity> findAll();

    Optional<ZadanieEntity> findOne(Long id);

    void delete(Long id);
}
