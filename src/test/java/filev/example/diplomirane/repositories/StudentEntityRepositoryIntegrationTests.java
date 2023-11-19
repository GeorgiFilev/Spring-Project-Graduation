package filev.example.diplomirane.repositories;


import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentEntityRepositoryIntegrationTests {


    private StudentRepository underTest;


    @Autowired
    public StudentEntityRepositoryIntegrationTests(StudentRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatStudentCanBeCreatedAndRecalled(){
        StudentEntity studentEntity = TestDataUtil.createStudentA();
        underTest.save(studentEntity);
        Optional<StudentEntity> result = underTest.findById(studentEntity.getId());
        assertThat(result).isPresent();
    }

    @Test
    public void testThatMultipleStudentsCanBeCreatedAndRecalled(){
        StudentEntity studentEntityA = TestDataUtil.createStudentA();
        StudentEntity studentEntityB = TestDataUtil.createStudentB();
        underTest.save(studentEntityA);
        underTest.save(studentEntityB);

//        studentEntityA.getZadaniq().size();
//        studentEntityB.getZadaniq().size();

        Iterable<StudentEntity> result = underTest.findAll();
        assertThat(result).hasSize(2)
                ;

    }

    @Test
    public void testThatStudentCanBeDeleted(){
        StudentEntity studentEntityA = TestDataUtil.createStudentA();
        underTest.save(studentEntityA);

        underTest.deleteById(studentEntityA.getId());

        Optional<StudentEntity> result = underTest.findById(studentEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetStudentsWithName(){
        StudentEntity studentEntityA = TestDataUtil.createStudentA();
        underTest.save(studentEntityA);
        StudentEntity studentEntityB = TestDataUtil.createStudentB();
        underTest.save(studentEntityB);


        Iterable<StudentEntity> result =  underTest.getNameLike("Georgi");
        for (StudentEntity student : result) {
            System.out.println("Student Name: " + student.getName());
            assertThat(student.getName()).contains("Georgi");
        }

    }

    @Test
    public void testThatGetStudentsWithFNumber(){
        StudentEntity studentEntityA = TestDataUtil.createStudentA();
        underTest.save(studentEntityA);
        StudentEntity studentEntityB = TestDataUtil.createStudentB();
        underTest.save(studentEntityB);

        Iterable<StudentEntity> result =  underTest.getFNumberLike("F104081");
        for (StudentEntity student : result) {
            System.out.println("Student Name: " + student.getFNumber());
            assertThat(student.getFNumber()).contains("F104081");
        }
    }
}
