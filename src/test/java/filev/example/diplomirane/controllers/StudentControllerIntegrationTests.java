package filev.example.diplomirane.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import filev.example.diplomirane.TestDataUtil;

import filev.example.diplomirane.entities.Dto.StudentDto;
import filev.example.diplomirane.entities.models.StudentEntity;

import filev.example.diplomirane.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTests {

    private StudentService studentService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    // trqbwashe da dobawq towa otdolu s webApplicationContext
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach()
    public void setup()
    {
        //Init MockMvc Object and build
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    public StudentControllerIntegrationTests(MockMvc mockMvc, StudentService studentService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatCreateStudentSuccessfullyReturnsHttp201Created() throws Exception {
        StudentEntity studentEntity = TestDataUtil.createStudentA();
        studentEntity.setId(null);
        String studentJson = objectMapper.writeValueAsString(studentEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListStudentsReturnsHttpStatus200() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListStudentsReturnsListStudents() throws Exception{
        StudentEntity studentEntity = TestDataUtil.createStudentA();
        studentEntity.setId(null); //just testing

        studentService.save(studentEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        );
    }


    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListStudentsReturnsHttpStatus200WhenStudentExists() throws Exception {
        StudentEntity studentEntity = TestDataUtil.createStudentA();

        studentService.save(studentEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        );
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatFullUpdateStudentReturnsHttpStatusOf200WhenStudentExists() throws Exception {

        StudentEntity studentEntity = TestDataUtil.createStudentA();
        StudentEntity savedStudentA = studentService.save(studentEntity);

        StudentDto studentDto = TestDataUtil.createTestStudentDtoA();
        String studentDtoJson = objectMapper.writeValueAsString(studentDto);


        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/" + savedStudentA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatDeleteStudentReturnsHttpStatus204ForNonExistingStudent() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/" + 99)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatDeleteStudentReturnsHttpStatus204ForExistingStudent() throws Exception {

        StudentEntity studentEntity = TestDataUtil.createStudentA();
        StudentEntity savedStudentA = studentService.save(studentEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/" + savedStudentA.getId())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatGetStudentsWhereNameLikeParamReturnsHttpStatus200() throws Exception {
        StudentEntity studentEntity = TestDataUtil.createStudentA();
        StudentEntity savedStudentA = studentService.save(studentEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/namesLike/Georgi" )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatGetStudentsWhereFnumberLikeParamReturnsHttpStatus200() throws Exception {
        StudentEntity studentEntity = TestDataUtil.createStudentA();
        StudentEntity savedStudentA = studentService.save(studentEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/FnumberLike/F104081" )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
