package filev.example.diplomirane.controllers;


import filev.example.diplomirane.entities.Dto.StudentDto;
import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.mappers.Mapper;
import filev.example.diplomirane.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private StudentService studentService;
    private Mapper<StudentEntity,StudentDto> studentMapper;

    public StudentController(StudentService studentService,Mapper<StudentEntity, StudentDto> studentMapper) {
        this.studentService=studentService;
        this.studentMapper = studentMapper;

    }

    @PostMapping(path="/students")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);
        StudentEntity savedStundentEntity = studentService.save(studentEntity);
        return new ResponseEntity<>(studentMapper.mapTo(savedStundentEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/students")
    public List<StudentDto> listStudents(){
        List<StudentEntity> students = studentService.findAll();
        return students.stream()
                .map(studentMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/students/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("id") Long id){
        Optional<StudentEntity> foundStudent = studentService.findOne(id);
        return foundStudent.map(studentEntity -> {
            StudentDto studentDto = studentMapper.mapTo(studentEntity);
            return new ResponseEntity(studentDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping(path = "/students/{id}")
    public ResponseEntity<StudentDto> fullUpdateStudent(
            @PathVariable("id") Long id,
            @RequestBody StudentDto studentDto
    ){
        if(!studentService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentDto.setId(id);
        StudentEntity studentEntity = studentMapper.mapFrom(studentDto);
        StudentEntity savedStudentEntity = studentService.save(studentEntity);

        return new ResponseEntity<>(
                studentMapper.mapTo(studentEntity),HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id")Long id){
        studentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping(path = "/students/namesLike/{name}")
    public List<StudentDto> listStudentsNameLike(@PathVariable("name")String name){
        List<StudentEntity> students = studentService.getNameLike(name);
        return students.stream()
                .map(studentMapper::mapTo)
                .collect(Collectors.toList());
    }


    @GetMapping(path = "/students/FnumberLike/{number}")
    public List<StudentDto> listStudentsFnumberLike(@PathVariable("number")String number){
        List<StudentEntity> students = studentService.getFNumberLike(number);
        return students.stream()
                .map(studentMapper::mapTo)
                .collect(Collectors.toList());
    }
}
