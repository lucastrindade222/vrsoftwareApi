package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.dto.BrandNew;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import br.com.lucas.vrsoftwareApi.repository.BrandRepository;
import br.com.lucas.vrsoftwareApi.repository.CostumersRepository;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.repository.RentaisRepository;
import br.com.lucas.vrsoftwareApi.service.BrandService;
import br.com.lucas.vrsoftwareApi.service.RentaisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class RentaisResourceTest extends AplicationConfingTest {
    @Autowired
    private RentaisService rentaisService;
    @MockBean
    private RentaisRepository rentaisRepository;
    @MockBean
    private CostumersRepository costumersRepository;
    @MockBean
    private CrasRepository crasRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findAllTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/rentais")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/rentais/find?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(100));
    }
    @Test
    public void findByIdErroTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/rentais/find?id=20"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void findPageTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/rentais/page"));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void saveTest() throws Exception {
        var rentaisNew = new RentaisNew(1,1, LocalDate.now().plusDays(40),10);
        ResultActions response=  mockMvc.perform(post("/rentais")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rentaisNew)
                ));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void extendTheLeasePeriod() throws Exception {

        ResultActions response=  mockMvc.perform(put("/rentais/extend-period?rentai_id=2&plus_days=2"));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void deleteTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/rentais?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void deleteErrTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/rentais?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @BeforeEach
    public void setup() {
        var cra = new Cras();
        cra.setDaily_rate(new BigDecimal("100"));
        Mockito.when( this.crasRepository.findById(1)).thenReturn(Optional.of(cra));

        var rentais1 = new Rentais();
        rentais1.setStart_date(LocalDateTime.now().plusDays(1));
        rentais1.setEnd_date(LocalDateTime.now().plusDays(5));
        rentais1.setTotal(new BigDecimal("100"));
        rentais1.setCras(cra);
        var rentais2 = new Rentais();
        rentais2.setStart_date(LocalDateTime.now().plusDays(100));
        rentais2.setEnd_date(LocalDateTime.now().plusDays(150));
        rentais2.setTotal(new BigDecimal("150"));
        rentais2.setCras(cra);

        Mockito.when( this.rentaisRepository.findAll()).thenReturn(Arrays.asList(rentais1,rentais2));
        Mockito.when( this.rentaisRepository.findById(2)).thenReturn(Optional.of(rentais1));
        Mockito.when( this.rentaisRepository.findById(13)).thenReturn(Optional.empty());



        var costumers = new Costumers();
        Mockito.when( this.costumersRepository.findById(1)).thenReturn(Optional.of(costumers));



    }


}
