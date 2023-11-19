package filev.example.diplomirane.repositories;

import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
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
public class DiplomnaZashtitaEntityRepositoryIntegrationTests {

    private DiplomnaZashtitaRepository underTest;
    private StudentRepository studentRepository;


    @Autowired
    public DiplomnaZashtitaEntityRepositoryIntegrationTests(DiplomnaZashtitaRepository underTest,StudentRepository studentRepository){
        this.underTest = underTest;
        this.studentRepository = studentRepository;
    }

    @Test
    public void testThatDiplomnaZashtitaCanBeCreatedAndRecalled(){
        DiplomnaZashtitaEntity diplomnaZashtitaEntity = TestDataUtil.createDiplomnaZashtitaA();
        underTest.save(diplomnaZashtitaEntity);
        Optional<DiplomnaZashtitaEntity> result = underTest.findById(diplomnaZashtitaEntity.getId());
        assertThat(result).isPresent();
    }

    @Test
    public void testThatDiplomnaZashtitaWithStudentCanBeCreated(){

        StudentEntity studentEntity = TestDataUtil.createStudentA();
        studentRepository.save(studentEntity);

        DiplomnaZashtitaEntity diplomnaZashtitaEntity = TestDataUtil.createDiplomnaZashtitaA();
        diplomnaZashtitaEntity.setStudent(studentEntity);
        System.out.println(diplomnaZashtitaEntity.toString());
        underTest.save(diplomnaZashtitaEntity);

        Optional<DiplomnaZashtitaEntity> result = underTest.findById(diplomnaZashtitaEntity.getId());
        assertThat(result).isPresent();
    }


    @Test
    public  void testThatMultipleDiplomnaZashtitaCanBeCreatedAndRecalled(){

        StudentEntity studentEntity = TestDataUtil.createStudentA();
        studentRepository.save(studentEntity);


        DiplomnaZashtitaEntity diplomnaZashtitaEntity = TestDataUtil.createDiplomnaZashtitaA();
        DiplomnaZashtitaEntity diplomnaZashtitaEntityB = TestDataUtil.createDiplomnaZashtitaA();
        diplomnaZashtitaEntityB.setId(2L);
//        diplomnaZashtitaEntity.setStudent(studentEntity);
        System.out.println(diplomnaZashtitaEntity.toString());
        underTest.save(diplomnaZashtitaEntity);
        underTest.save(diplomnaZashtitaEntityB);


        Iterable<DiplomnaZashtitaEntity> result = underTest.findAll();

        assertThat(result).hasSize(2);
//                .contains(diplomnaZashtitaEntity);

    }
}
