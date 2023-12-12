package filev.example.diplomirane.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import filev.example.diplomirane.TestDataUtil;
import filev.example.diplomirane.entities.models.PrepodavatelEntity;
import filev.example.diplomirane.services.PrepodavatelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class PrepodavatelControllerIntegrationTests {

    private PrepodavatelService prepodavatelService;

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
    public PrepodavatelControllerIntegrationTests(PrepodavatelService prepodavatelService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.prepodavatelService = prepodavatelService;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatCreatePrepodavatelSuccessfullyReturnsHttp201Created() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());

        PrepodavatelEntity testPrepodavatelA = TestDataUtil.createPrepodavatelA();
        testPrepodavatelA.setId(null);
        String prepodavatelJson = objectMapper.writeValueAsString(testPrepodavatelA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/prepodavatels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prepodavatelJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }
    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListPrepodavatelsReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/prepodavatels")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        PrepodavatelEntity prepodavatelA = TestDataUtil.createPrepodavatelA();
        prepodavatelService.save(prepodavatelA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/prepodavatels")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        );
    }

    @Test
    @WithMockUser(username = "emilia" , authorities = {"Prepodavatel", "Student" })
    public void testThatListAuthorsReturnsHttpStatus200WhenAuthorExists() throws Exception {
        PrepodavatelEntity prepodavatelA = TestDataUtil.createPrepodavatelA();
        prepodavatelService.save(prepodavatelA);
        PrepodavatelEntity prepodavatelB = TestDataUtil.createPrepodavatelB();
        prepodavatelService.save(prepodavatelB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/prepodavatels/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }



}
