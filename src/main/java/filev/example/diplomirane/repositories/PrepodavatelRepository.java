package filev.example.diplomirane.repositories;

import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.entities.models.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrepodavatelRepository extends CrudRepository<PrepodavatelEntity, Long> {

    @Query("SELECT s FROM PrepodavatelEntity s WHERE s.name LIKE %:name%")
    Iterable<PrepodavatelEntity> getNameLike(@Param("name") String name);
}
