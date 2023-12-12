package filev.example.diplomirane.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import filev.example.diplomirane.TestDataUtil;

import filev.example.diplomirane.entities.models.StudentEntity;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.services.ZadanieService;
import org.aspectj.lang.annotation.Before;
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

import java.util.Optional;



import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ZadanieControllerIntegrationTests {

    private ZadanieService zadanieService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach()
    public void setup()
    {
        //Init MockMvc Object and build
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Autowired
    public ZadanieControllerIntegrationTests(ZadanieService zadanieService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.zadanieService = zadanieService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateZadanieSuccessfullyReturnsHttp201Created() throws Exception {
        ZadanieEntity testZadanieA = TestDataUtil.createZadanieA();
        testZadanieA.setId(null);
        String zadanieJson = objectMapper.writeValueAsString(testZadanieA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(zadanieJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }


    @Test
    public void testThatListZadaniesReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/zadanies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatDeleteZadanieReturnsHttpStatus204ForNonExistingZadanie() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/zadanies/" + 99)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingZadanie() throws Exception {

        ZadanieEntity testZadanieA = TestDataUtil.createZadanieA();
        ZadanieEntity savedZadanieA = zadanieService.save(testZadanieA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/zadanies/" + savedZadanieA.getId())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    //тест за всички одобрени задания
    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatGetZadaniesWhereOdobrenoLikeYesReturnsHttpStatus200() throws Exception {
        ZadanieEntity testZadanieA = TestDataUtil.createZadanieA();
        ZadanieEntity savedZadanieA = zadanieService.save(testZadanieA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/zadanies/odobreno/yes" )
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
