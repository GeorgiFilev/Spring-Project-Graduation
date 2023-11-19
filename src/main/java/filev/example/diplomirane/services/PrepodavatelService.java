package filev.example.diplomirane.services;

import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.entities.models.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface PrepodavatelService {
    PrepodavatelEntity save(PrepodavatelEntity prepodavatelEntity);

    List<PrepodavatelEntity> findAll();

    Optional<PrepodavatelEntity> findOne(Long id);

    List<PrepodavatelEntity> getNameLike(String name);

    boolean isExists(Long id);

    void delete(Long id);
}
