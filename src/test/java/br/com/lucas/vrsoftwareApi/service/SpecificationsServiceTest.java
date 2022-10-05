package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.model.Specifications;
import br.com.lucas.vrsoftwareApi.repository.SpecificationsRepository;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.utils.UTILS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

@DisplayName("SpecificationsServiceImplemete")
public class SpecificationsServiceTest extends AplicationConfingTest {
    @Autowired
    public SpecificationsService specificationsService;
    @MockBean
    public SpecificationsRepository specificationsRepository;

    @Test
    public void findTest(){

        this.specificationsService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.specificationsService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Especificações não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.specificationsService.findAll();

    }

    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "id";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.specificationsService.pages(pageRequest);


    }

    @Test
    public void saveTest(){
        String description = "Com consumo médio de 6.4 litros/100km, 0 aos 100 km/h em 5.9 segundos, velocidade máxima de 250 km/h, um peso de 1505 kgs, o F30 3 Series Sedan 328i está equipado com um motor Em linha de 4 cilindros turbocompressor, a Gasolina.";
        var specifications = new Specifications(null,"F30",description,new Date(),null);

        this.specificationsService.save(specifications);

    }
    @Test
    public  void deliteTest(){

        this.specificationsService.delete(12);

    }
    @Test
    public  void deliteErrTest(){
        try {
            this.specificationsService.delete(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Especificações não encontrada com o id:13");
        }
    }



    @BeforeEach
    public void setup() {
        String description = "Com consumo médio de 6.4 litros/100km, 0 aos 100 km/h em 5.9 segundos, velocidade máxima de 250 km/h, um peso de 1505 kgs, o F30 3 Series Sedan 328i está equipado com um motor Em linha de 4 cilindros turbocompressor, a Gasolina.";
        var specifications = new Specifications(null,"F30",description,new Date(),null);

        Mockito.when( this.specificationsRepository.findById(12)).thenReturn(Optional.of(specifications));
        Mockito.when( this.specificationsRepository.findById(13)).thenReturn(Optional.empty());

    }
}
