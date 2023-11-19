package filev.example.diplomirane.services;

import filev.example.diplomirane.entities.models.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentEntity save(StudentEntity studentEntity);

    List<StudentEntity> findAll();

    Optional<StudentEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);


    List<StudentEntity> getNameLike(String name);

    List<StudentEntity> getFNumberLike(String number);

}
