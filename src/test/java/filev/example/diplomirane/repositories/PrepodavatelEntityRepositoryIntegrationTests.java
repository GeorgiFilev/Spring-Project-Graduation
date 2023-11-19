package filev.example.diplomirane.repositories;


import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.PrepodavatelEntity;
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
public class PrepodavatelEntityRepositoryIntegrationTests {
    private PrepodavatelRepository underTest;


    @Autowired
    public PrepodavatelEntityRepositoryIntegrationTests(PrepodavatelRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatPrepodavatelCanBeCreatedAndRecalled(){
        PrepodavatelEntity prepodavatelEntity = TestDataUtil.createPrepodavatelA();
        underTest.save(prepodavatelEntity);
        Optional<PrepodavatelEntity> result = underTest.findById(prepodavatelEntity.getId());
        assertThat(result).isPresent();
    }

    @Test
    public  void testThatMultiplePrepodavatelsCanBeCreatedAndRecalled(){
        PrepodavatelEntity prepodavatelEntityA = TestDataUtil.createPrepodavatelA();
        PrepodavatelEntity prepodavatelEntityB = TestDataUtil.createPrepodavatelB();
        underTest.save(prepodavatelEntityA);
        underTest.save(prepodavatelEntityB);

        Iterable<PrepodavatelEntity> result = underTest.findAll();
        result.forEach(prepodavatel -> {
            if(prepodavatel.getZadaniq() == null || prepodavatel.getZadaniq().size() == 0){
                prepodavatel.setZadaniq(null);
            }
        } );

        assertThat(result).hasSize(2)
                .contains(prepodavatelEntityA, prepodavatelEntityB);

    }

    @Test
    public void testThatPrepodavatelCanBeDeleted(){
        PrepodavatelEntity a = TestDataUtil.createPrepodavatelA();
        underTest.save(a);

        underTest.deleteById(a.getId());
        Optional<PrepodavatelEntity> result = underTest.findById(a.getId());
        assertThat(result).isEmpty();
    }

    @Test void testThatPrepodavatelHaveDlujnost(){
        PrepodavatelEntity prepodavatelEntityA = TestDataUtil.createPrepodavatelA();
        underTest.save(prepodavatelEntityA);
        PrepodavatelEntity prepodavatelEntityB = TestDataUtil.createPrepodavatelB();
        underTest.save(prepodavatelEntityB);

//        underTest.deleteById(a.getId());
//        Optional<PrepodavatelEntity> result = underTest.findById(a.getId());
//        assertThat(result).isEmpty();
    }
}
