package filev.example.diplomirane.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.DiplomnaZashtitaEntity;
import filev.example.diplomirane.entities.models.ZadanieEntity;
import filev.example.diplomirane.services.DiplomnaZashtitaService;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DiplomnaZashtitaControllerIntegration {
    private DiplomnaZashtitaService diplomnaZashtitaService;
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
    public DiplomnaZashtitaControllerIntegration(DiplomnaZashtitaService diplomnaZashtitaService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.diplomnaZashtitaService = diplomnaZashtitaService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateDiplomnaZashtitaSuccessfullyReturnsHttp201Created() throws Exception {
        DiplomnaZashtitaEntity diplomnaZashtita = TestDataUtil.createDiplomnaZashtitaA();
        diplomnaZashtita.setId(2L);
        String diplomnaZashtitaJson = objectMapper.writeValueAsString(diplomnaZashtita);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/diplomnaZashtitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(diplomnaZashtitaJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatDeleteDiplomnaZashtitaReturnsHttpStatus204ForNonExistingDiplomnaZashtita() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/diplomnaZashtitas/" + 99)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
