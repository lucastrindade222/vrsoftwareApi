package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.repository.CarsImagesRepository;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.utils.UTILS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;



import java.io.FileInputStream;
import java.io.IOException;

import java.util.Optional;


@DisplayName("CarsImagesServiceImplemete")
public class CarsImagesServiceTest extends AplicationConfingTest {

    @Autowired
    public CarsImagesService carsImagesService;
    @MockBean
    public CarsImagesRepository carsImagesRepository;

    @Test
    public void findTest(){

        this.carsImagesService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.carsImagesService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Image não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.carsImagesService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.carsImagesService.pages(pageRequest);


    }
    @Test
    public void saveTest(){

        try {
            FileInputStream file = new FileInputStream("img/Hobbit.jpg");
            this.carsImagesService.save(12,file.readAllBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public  void deliteTest(){

        this.carsImagesService.delete(12);

    }
    @Test
    public  void deliteErrTest(){
        try {
            this.carsImagesService.delete(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Image não encontrada com o id:13");
        }
    }


    @BeforeEach
    public void setup() {
        var carsImages = new CarsImages();

        Mockito.when( this.carsImagesRepository.findById(12)).thenReturn(Optional.of(carsImages));
        Mockito.when( this.carsImagesRepository.findById(13)).thenReturn(Optional.empty());

    }
}
