package filev.example.diplomirane.repositories;

import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZadanieRepository extends CrudRepository<ZadanieEntity, Long> {
    Iterable<ZadanieEntity> technologiiContains(String s);

    @Query("SELECT a from ZadanieEntity a where a.odobreno LIKE %:string%")
    List<ZadanieEntity> findByOdobreno(@Param("string") String odobreno);
}
