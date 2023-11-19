package filev.example.diplomirane.repositories;

import filev.example.diplomirane.entities.models.ZadanieEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZadanieRepository extends CrudRepository<ZadanieEntity, Long> {
    Iterable<ZadanieEntity> technologiiContains(String s);
}
