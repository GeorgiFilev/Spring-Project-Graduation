package filev.example.diplomirane.services.impl;

import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.repositories.PrepodavatelRepository;
import filev.example.diplomirane.services.PrepodavatelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PrepodavatelServiceImpl implements PrepodavatelService {
    private PrepodavatelRepository prepodavatelRepository;

    public PrepodavatelServiceImpl(PrepodavatelRepository prepodavatelRepository) {
        this.prepodavatelRepository = prepodavatelRepository;
    }

    @Override
    public PrepodavatelEntity save(PrepodavatelEntity prepodavatelEntity) {
        return prepodavatelRepository.save(prepodavatelEntity);
    }

    @Override
    public List<PrepodavatelEntity> findAll() {
        return StreamSupport.stream(prepodavatelRepository
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<PrepodavatelEntity> findOne(Long id) {
        return prepodavatelRepository.findById(id);
    }

    @Override
    public List<PrepodavatelEntity> getNameLike(String name) {
        return StreamSupport.stream(prepodavatelRepository
                        .getNameLike(name)
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return prepodavatelRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        prepodavatelRepository.deleteById(id);
    }


}
