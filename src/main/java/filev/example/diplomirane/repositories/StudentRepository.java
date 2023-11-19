package filev.example.diplomirane.repositories;

import filev.example.diplomirane.entities.models.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Long> {

    @Query("SELECT s FROM StudentEntity s WHERE s.name LIKE %:name%")
    Iterable<StudentEntity> getNameLike(@Param("name") String name);

    @Query("SELECT s FROM StudentEntity s WHERE s.fNumber LIKE %:number%")
    Iterable<StudentEntity> getFNumberLike(@Param("number") String number);
}
