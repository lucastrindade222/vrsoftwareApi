package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.CarsImagesRepository;
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



import java.io.FileInputStream;
import java.io.IOException;

import java.util.Optional;


@DisplayName("CarsImagesServiceImplemete")
public class CarsImagesServiceTest extends AplicationConfingTest {

    @Autowired
    public CarsImagesService carsImagesService;
    @MockBean
    public CarsImagesRepository carsImagesRepository;
    @MockBean
    public CrasRepository crasRepository;

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
            this.carsImagesService.save(10,file.readAllBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void upDataTest(){

        try {
            FileInputStream file = new FileInputStream("img/Hobbit.jpg");
            this.carsImagesService.save(15,file.readAllBytes());

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
        var cra1 = new Cras();
        var cra2 = new Cras();
         cra2.setId(15);
        Mockito.when( this.crasRepository.findById(10)).thenReturn(Optional.of(cra1));
        Mockito.when( this.crasRepository.findById(15)).thenReturn(Optional.of(cra2));
        var carsImages1 = new CarsImages();
        var carsImages2 = new CarsImages();

        carsImages2.setCar(cra2);
        Mockito.when( this.carsImagesRepository.findById(12)).thenReturn(Optional.of(carsImages1));
        Mockito.when( this.carsImagesRepository.findById(88)).thenReturn(Optional.of(carsImages2));
        Mockito.when( this.carsImagesRepository.findById(13)).thenReturn(Optional.empty());




    }
}
