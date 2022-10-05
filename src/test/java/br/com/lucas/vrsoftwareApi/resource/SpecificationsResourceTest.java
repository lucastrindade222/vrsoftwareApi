package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.dto.SpecificationsNew;
import br.com.lucas.vrsoftwareApi.model.Specifications;
import br.com.lucas.vrsoftwareApi.repository.SpecificationsRepository;
import br.com.lucas.vrsoftwareApi.service.SpecificationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@DisplayName("SpecificationsResource")
public class SpecificationsResourceTest  extends AplicationConfingTest {
    @Autowired
    private SpecificationsService specificationsService;
    @MockBean
    private SpecificationsRepository specificationsRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ModelMapper modelMapper;
    @Test
    public void findAllTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/specifications")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/specifications/find?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("F30"));
    }
    @Test
    public void findByIdErroTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/specifications/find?id=20"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void findPageTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/specifications/page"));



    }
    @Test
    public void saveTest() throws Exception {
        String description = "Com consumo médio de 6.4 litros/100km, 0 aos 100 km/h em 5.9 segundos, velocidade máxima de 250 km/h, um peso de 1505 kgs, o F30 3 Series Sedan 328i está equipado com um motor Em linha de 4 cilindros turbocompressor, a Gasolina.";

        var categoryNew = new SpecificationsNew("F30",description);
        ResultActions response=  mockMvc.perform(post("/specifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryNew)
                ));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void deleteTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/specifications?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void deleteErrTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/specifications?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @BeforeEach
    public void setup() {

        String description = "Com consumo médio de 6.4 litros/100km, 0 aos 100 km/h em 5.9 segundos, velocidade máxima de 250 km/h, um peso de 1505 kgs, o F30 3 Series Sedan 328i está equipado com um motor Em linha de 4 cilindros turbocompressor, a Gasolina.";
        var specifications1 = new Specifications(1,"F30",description,new Date(),null);
        var specifications2 = new Specifications(2,"F32",description,new Date(),null);
        Mockito.when( this.specificationsRepository.findAll()).thenReturn(Arrays.asList(specifications1,specifications2));

        Mockito.when( this.specificationsRepository.findById(1)).thenReturn(Optional.of(specifications1));



    }
}
