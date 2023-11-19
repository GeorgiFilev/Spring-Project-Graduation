package filev.example.diplomirane.services.impl;

import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.repositories.StudentRepository;
import filev.example.diplomirane.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> findAll() {
        return StreamSupport.stream(studentRepository
                        .findAll()
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentEntity> findOne(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentEntity> getNameLike(String name) {
        return StreamSupport.stream(studentRepository
                        .getNameLike(name)
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentEntity> getFNumberLike(String number) {
        return StreamSupport.stream(studentRepository
                .getFNumberLike(number)
                        .spliterator(),false)
                .collect(Collectors.toList());
    }

//    public void getNameLike(String string){
//        studentRepository.getNameLike(string);
//    }


}
