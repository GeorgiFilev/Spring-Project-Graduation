package filev.example.diplomirane;

import filev.example.diplomirane.entities.Dto.StudentDto;
import filev.example.diplomirane.entities.models.*;

import java.util.Date;

public class TestDataUtil {
    private TestDataUtil(){}

    public static StudentEntity createStudentA() {


        return StudentEntity.builder()
                .id(1L)
                .name("Georgi Filev")
                .fNumber("F104081")
                .zadaniq(null)
                .build();
    }

    public static StudentEntity createStudentB() {

        return StudentEntity.builder()
                .id(2L)
                .name("Emilia Vasileva")
                .fNumber("F104021")
                .zadaniq(null)
                .build();
    }

    public static ZadanieEntity createZadanieA() {
        return ZadanieEntity.builder()
                .id(1L)
                .tema("Programirane")
                .purpose("Obicham da izkarvam 6tici")
                .prepodavatel(null)
                .student(null)
                .technologii("js,typescript , flutter")
                .build();
    }

    public static PrepodavatelEntity createPrepodavatelA(){

        return PrepodavatelEntity.builder()
                .id(1L)
                .name("Hristina Kostadinova")
                .dlujnost("Prepodavatel sistemi")
                .zadaniq(null)
                .build();
    }
    public static PrepodavatelEntity createPrepodavatelB(){

        return PrepodavatelEntity.builder()
                .id(2L)
                .name("Zlatar Zlatarov")
                .dlujnost("Prepodavatel sistemi, OOP")
                .zadaniq(null)
                .build();
    }

    public static DiplomnaZashtitaEntity createDiplomnaZashtitaA(){

        return DiplomnaZashtitaEntity.builder()
                .id(1L)
                .mark("Otlichen 4")
                .student(null)
                .dateUploaded(new Date())
                .build();
    }

    public static StudentDto createTestStudentDtoA() {
        return StudentDto.builder()
                .id(1L)
                .name("Ivan Vasoz")
                .fNumber("f10000")
                .diplomnaZashtita(null)
                .build();
    }


//    public static AuthorDto createTestAutorDtoA() {
//        return AuthorDto.builder()
//                .id(1L)
//                .name("Abigail Rose")
//                .age(80)
//                .build();
//    }
//
//
//    public static AuthorEntity createTestAuthorA() {
//        return AuthorEntity.builder()
//                .id(1L)
//                .name("Abigail Rose")
//                .age(80)
//                .build();
//    }
//    public static AuthorEntity createTestAuthorB() {
//        return AuthorEntity.builder()
//                .id(2L)
//                .name("Abigail Dough")
//                .age(60)
//                .build();
//    }
//    public static BookEntity createBookAWithAuthorA(final AuthorEntity authorEntity) {
//        return BookEntity.builder()
//                .isbn("978-657")
//                .title("Book 1")
//                .authorEntityId(authorEntity)
//                .build();
//    }
}
