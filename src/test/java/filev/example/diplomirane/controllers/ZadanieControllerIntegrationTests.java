package filev.example.diplomirane.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import filev.example.diplomirane.TestDataUtil;

import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.services.ZadanieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void testThatDeleteZadanieReturnsHttpStatus204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/zadanies/" + 99)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {

        ZadanieEntity testZadanieA = TestDataUtil.createZadanieA();
        ZadanieEntity savedZadanieA = zadanieService.save(testZadanieA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + savedZadanieA.getId())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
