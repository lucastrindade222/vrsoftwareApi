package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.BrandNew;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.repository.CostumersRepository;
import br.com.lucas.vrsoftwareApi.service.CostumersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@DisplayName("CostumersResource")
public class CostumersResourceTest {
    @Autowired
    private CostumersService costumersService;
    @MockBean
    private CostumersRepository costumersRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findAllTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/costumers"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/costumers/find?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Ford"));
    }
    @Test
    public void findByIdErroTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/costumers/find?id=20"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void findPageTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/costumers/page"));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void saveTest() throws Exception {
        var brand = new BrandNew("BMW");
        ResultActions response=  mockMvc.perform(post("/costumers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brand)
                ));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void deleteTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/costumers?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void deleteErrTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/costumers?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @BeforeEach
    public void setup() {


        var costumers1 = new Costumers(1,"lucas 1",new Date(),"lucas@email.com","12354657","Teste","81981232222",new Date(),null,null);
        var costumers2 = new Costumers(2,"lucas 2",new Date(),"lucas12@email.com","123533","Teste 2","81981232222",new Date(),null,null);

        Mockito.when( this.costumersRepository.findAll()).thenReturn(Arrays.asList(costumers1,costumers1));

        Mockito.when( this.costumersRepository.findById(2)).thenReturn(Optional.of(costumers2));



    }
}
