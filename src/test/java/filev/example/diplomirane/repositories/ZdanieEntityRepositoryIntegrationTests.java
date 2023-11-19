package filev.example.diplomirane.repositories;

import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.ZadanieEntity;
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
public class ZdanieEntityRepositoryIntegrationTests {

    private ZadanieRepository underTest;


    @Autowired
    public ZdanieEntityRepositoryIntegrationTests(ZadanieRepository underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatZadanieCanBeCreatedAndRecalled(){
        ZadanieEntity zadanieEntity = TestDataUtil.createZadanieA();
        underTest.save(zadanieEntity);
        Optional<ZadanieEntity> result = underTest.findById(zadanieEntity.getId());
        assertThat(result).isPresent();
    }


    @Test
    public void testThatZadanieCanBeDeleted(){
        ZadanieEntity a = TestDataUtil.createZadanieA();
        underTest.save(a);

        underTest.deleteById(a.getId());

        Optional<ZadanieEntity> result = underTest.findById(a.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetZadaniesThatContainString(){
        ZadanieEntity zadanieEntity = TestDataUtil.createZadanieA();
        underTest.save(zadanieEntity);
        zadanieEntity.setTechnologii("C++");
        ZadanieEntity zadanieEntityB = TestDataUtil.createZadanieA();
        zadanieEntityB.setTechnologii("C++");
        underTest.save(zadanieEntityB);

        Iterable<ZadanieEntity> result =  underTest.technologiiContains("C++");
        assertThat(result).contains(zadanieEntity,zadanieEntityB);
    }

    @Test
    public void testThatGetAllZadaniesContainsZadanie(){
        ZadanieEntity zadanieEntity = TestDataUtil.createZadanieA();
        underTest.save(zadanieEntity);
        zadanieEntity.setTechnologii("C++");
        ZadanieEntity zadanieEntityB = TestDataUtil.createZadanieA();
        zadanieEntityB.setTechnologii("C++");
        underTest.save(zadanieEntityB);

        Iterable<ZadanieEntity> result =  underTest.findAll();
        assertThat(result).contains(zadanieEntity);
    }

}
