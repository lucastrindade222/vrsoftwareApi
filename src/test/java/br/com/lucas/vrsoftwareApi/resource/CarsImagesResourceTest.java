package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.CarsImagesRepository;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.service.CarsImagesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@DisplayName("CarsImagesResource")
public class CarsImagesResourceTest extends AplicationConfingTest {


    @Autowired
    private CarsImagesService carsImagesService;
    @MockBean
    private CarsImagesRepository carsImagesRepository;
    @MockBean
    private CrasRepository crasRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void findAllTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cars-images")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cars-images/find?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"));
    }
    @Test
    public void findByIdErroTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cars-images/find?id=20"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
    @Test
    public void findPageTest() throws Exception {

        ResultActions response=  mockMvc.perform(get("/cars-images/page"));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    public void saveTest() throws Exception {

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        ResultActions response=  mockMvc.perform(multipart("/cars-images/1").file(file));


        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    public void deleteTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/cars-images?id=2"));

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    public void deleteErrTest() throws Exception {

        ResultActions response=  mockMvc.perform(delete("/cars-images?id=1"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @BeforeEach
    public void setup() throws IOException {

        var brand = new Brand();

        String name = "sed?? compacto";
        String discretion = "O carro sedan ?? aquele em que a carroceria pode ser dividida em tr??s partes, sendo a ??ltima delas a traseira, que tem um volume bem maior e mais projetada do que em outros modelos de ve??culos.";
        var category = new Category(null,name,discretion,null,null);

        String description ="Esportivo, cl??ssico e voltado para o futuro: o lan??amento do BMW F30 em 14 de outubro de 2011 na f??brica da BMW em Munique, revelou os caminhos inovadores que a Bayerische Motoren Werke explorou nesta sexta vers??o do BMW S??rie 3. A apar??ncia marcante do BMW F30 se baseia na atual linha de";
        String license_plate="NAY-2083";
        var daily_rate = new BigDecimal("800.00");
        boolean available = true;
        String color = "Red";

        var car1 = new Cras(1,"F30",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null,null);
        var car2 = new Cras(2,"F34",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null,null);


        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        var carsImages1 = new CarsImages( 1,new Date(),car1,file.getBytes());
        var carsImages2 = new CarsImages( 2,new Date(),car2,file.getBytes());

        Mockito.when( this.carsImagesRepository.findAll()).thenReturn(Arrays.asList(carsImages1,carsImages2));

        Mockito.when( this.carsImagesRepository.findById(2)).thenReturn(Optional.of(carsImages2));

        Mockito.when( this.crasRepository.findById(1)).thenReturn(Optional.of(car1));

    }
}
