package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.CrasNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.BrandRepository;
import br.com.lucas.vrsoftwareApi.repository.CategoryRepository;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.service.CrasService;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@DisplayName("CrasResource")
public class CrasResourceTest extends AplicationConfingTest {

    @Autowired
    private CrasService crasService;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private BrandRepository brandRepository;
    @MockBean
    private CrasRepository crasRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findAllTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cras")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cras/find?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("F30"));
    }
    @Test
    public void findByIdErroTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cras/find?id=20"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void findPageTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cras/page"));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void consultTotalValue() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cras/consult?car_id=1&days=5"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").value("4000.0"));
    }
    @Test
    public void availableCars()throws Exception {
        CheckAvailability checkAvailability = new CheckAvailability(LocalDate.now(),10l);

        ResultActions response=  mockMvc.perform(post("/cras/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(checkAvailability)
                ));

        response.andExpect(MockMvcResultMatchers.status().isOk());


    }



    @Test
    public void saveTest() throws Exception {
       var crasNew = CrasNew.builder()
               .name("F34")
               .description("Esportivo, clássico e voltado para o futuro: o lançamento do BMW F30 em 14 de outubro de 2011 na fábrica da BMW em Munique.")
               .color("Res")
               .license_plate("NAY-2083")
               .daily_rate(new BigDecimal("800.00"))
               .category(1)
               .brand(1)
               .build();
        ResultActions response=  mockMvc.perform(post("/cras")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crasNew)
                ));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void deleteTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/cras?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void deleteErrTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/cras?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @BeforeEach
    public void setup() {

        var brand = new Brand();

        Mockito.when( this.brandRepository.findById(1)).thenReturn(Optional.of(brand));
        String name = "sedã compacto";
        String discretion = "O carro sedan é aquele em que a carroceria pode ser dividida em três partes, sendo a última delas a traseira, que tem um volume bem maior e mais projetada do que em outros modelos de veículos.";
        var category = new Category(null,name,discretion,null,null);

        Mockito.when( this.categoryRepository.findById(1)).thenReturn(Optional.of(category));

        String description ="Esportivo, clássico e voltado para o futuro: o lançamento do BMW F30 em 14 de outubro de 2011 na fábrica da BMW em Munique, revelou os caminhos inovadores que a Bayerische Motoren Werke explorou nesta sexta versão do BMW Série 3. A aparência marcante do BMW F30 se baseia na atual linha de";
        String license_plate="NAY-2083";
        var daily_rate = new BigDecimal("800.00");
        boolean available = true;
        String color = "Red";

        var car1 = new Cras(1,"F30",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null);
        var car2 = new Cras(2,"F34",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null);

        Mockito.when( this.crasRepository.findById(1)).thenReturn(Optional.of(car1));
        Mockito.when( this.crasRepository.findAll()).thenReturn(Arrays.asList(car1,car2));


    }


}
