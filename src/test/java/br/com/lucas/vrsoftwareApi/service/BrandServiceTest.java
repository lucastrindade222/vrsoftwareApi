package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.repository.BrandRepository;
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

@DisplayName("BrandServiceImplemete")
public class BrandServiceTest extends AplicationConfingTest {
    @Autowired
    public BrandService brandService;
    @MockBean
    public BrandRepository brandRepository;

    @Test
    public void findTest(){

        this.brandService.find(12);

    }
    @Test
    public void findErroTest(){
       try {
           this.brandService.find(13);
           Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
       }catch (ObjectNotFoundException err){
        Assertions.assertEquals(err.getMessage(),"Marca não encontrada com o id:13");
       }


    }
    @Test
    public void findAllTest(){

        this.brandService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
         this.brandService.pages(pageRequest);


    }

    @Test
    public void saveTest(){
        var brand = new Brand();
        brand.setName("subaru");
        this.brandService.save(brand);

    }
    @Test
    public  void deliteTest(){

        this.brandService.delete(12);

    }
    @Test
    public  void deliteErrTest(){
        try {
        this.brandService.delete(13);
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
