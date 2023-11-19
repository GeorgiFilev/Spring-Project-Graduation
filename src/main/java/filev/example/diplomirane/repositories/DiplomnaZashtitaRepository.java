package filev.example.diplomirane.repositories;

import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomnaZashtitaRepository extends CrudRepository<DiplomnaZashtitaEntity, Long> {

}
