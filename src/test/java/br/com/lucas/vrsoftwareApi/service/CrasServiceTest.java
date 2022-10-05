package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.CrasNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.BrandRepository;
import br.com.lucas.vrsoftwareApi.repository.CategoryRepository;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.utils.UTILS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@DisplayName("CrasServiceImplemete")
public class CrasServiceTest extends AplicationConfingTest {
    @Autowired
    public CrasService crasService;
    @MockBean
    public CrasRepository crasRepository;

     @MockBean
    public CategoryRepository categoryRepository;
    @MockBean
    public BrandRepository brandRepository;




    @Test
    public void findTest(){

        this.crasService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.crasService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Carros não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.crasService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.crasService.pages(pageRequest);


    }
    @Test
    public void availableCars(){
        CheckAvailability checkAvailability = new CheckAvailability(LocalDate.now(),10l);
        this.crasService.availableCars(checkAvailability);
    }

    @Test
    public void saveTest(){

        var car = CrasNew.builder()
                .daily_rate(new BigDecimal("710.00"))
                .color("Red")
                .name("F30")
                .description( "Esportivo, clássico e voltado para o futuro: o lançamento do BMW F30 em 14 de outubro de 2011 na fábrica da BMW em Munique, revelou os caminhos inovadores que a Bayerische Motoren Werke explorou nesta sexta versão do BMW Série 3. A aparência marcante do BMW F30 se baseia na atual linha de")
                .category(1)
                .brand(1)
                .license_plate("NAT-6399")
                .build();

         this.crasService.save(car);


    }
    @Test
    public  void deliteTest(){

        this.crasService.delete(12);

    }
    @Test
    public  void deliteErrTest(){
        try {
            this.crasService.delete(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Carros não encontrada com o id:13");
        }
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

        var car = new Cras(12,"F34",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null);

        Mockito.when( this.crasRepository.findById(12)).thenReturn(Optional.of(car));
        Mockito.when( this.crasRepository.findById(13)).thenReturn(Optional.empty());


    }
}
