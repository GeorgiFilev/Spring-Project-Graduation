package filev.example.diplomirane.services;


import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.repositories.StudentRepository;
import filev.example.diplomirane.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class StudentServiceTests {
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    public void testSaveStudent() {
        StudentEntity student = new StudentEntity();
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(student);

        StudentEntity savedStudent = studentService.save(student);

        assertThat(savedStudent).isNotNull();
        verify(studentRepository, times(1)).save(any(StudentEntity.class));
    }

//    @Test
//    public void testFindAllStudents() {
//        List<StudentEntity> students = new ArrayList<>();
//        when(studentRepository.findAll()).thenReturn(students);
//
//        List<StudentEntity> foundStudents = studentService.findAll();
//
//        assertThat(foundStudents).isEqualTo(students);
//        verify(studentRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindOneStudent() {
//        Long studentId = 1L;
//        StudentEntity student = new StudentEntity();
//        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
//
//        Optional<StudentEntity> foundStudent = studentService.findOne(studentId);
//
//        assertThat(foundStudent).isPresent();
//        verify(studentRepository, times(1)).findById(studentId);
//    }
//
//    @Test
//    public void testIsStudentExists() {
//        Long studentId = 1L;
//        when(studentRepository.existsById(studentId)).thenReturn(true);
//
//        boolean exists = studentService.isExists(studentId);
//
//        assertThat(exists).isTrue();
//        verify(studentRepository, times(1)).existsById(studentId);
//    }
//
//    @Test
//    public void testDeleteStudent() {
//        Long studentId = 1L;
//        studentService.delete(studentId);
//
//        verify(studentRepository, times(1)).deleteById(studentId);
//    }
//
//    @Test
//    public void testGetNameLike() {
//        String name = "John";
//        List<StudentEntity> students = new ArrayList<>();
//        when(studentRepository.getNameLike(name)).thenReturn(students);
//
//        List<StudentEntity> foundStudents = studentService.getNameLike(name);
//
//        assertThat(foundStudents).isEqualTo(students);
//        verify(studentRepository, times(1)).getNameLike(name);
//    }
//
//    @Test
//    public void testGetFNumberLike() {
//        String fNumber = "F123";
//        List<StudentEntity> students = new ArrayList<>();
//        when(studentRepository.getFNumberLike(fNumber)).thenReturn(students);
//
//        List<StudentEntity> foundStudents = studentService.getFNumberLike(fNumber);
//
//        assertThat(foundStudents).isEqualTo(students);
//        verify(studentRepository, times(1)).getFNumberLike(fNumber);
//    }
}
