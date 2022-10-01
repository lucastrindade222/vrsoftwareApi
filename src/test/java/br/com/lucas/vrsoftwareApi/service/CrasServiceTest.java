package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Cras;
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

import java.util.Optional;

@DisplayName("CrasServiceImplemete")
public class CrasServiceTest {
    @Autowired
    public CrasService crasService;
    @MockBean
    public CrasRepository crasRepository;


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
            Assertions.assertEquals(err.getMessage(),"Marca não encontrada com o id:13");
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
    public void saveTest(){
        var cras = new Cras();
        cras.setName("subaru");



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
            Assertions.assertEquals(err.getMessage(),"Marca não encontrada com o id:13");
        }
    }



    @BeforeEach
    public void setup() {
        var brand = new Brand();

        Mockito.when( this.brandRepository.findById(12)).thenReturn(Optional.of(brand));
        Mockito.when( this.brandRepository.findById(13)).thenReturn(Optional.empty());

    }
}
