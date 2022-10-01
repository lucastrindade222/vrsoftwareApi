package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.repository.CostumersRepository;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.service.exception.UniqueFieldException;
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

@DisplayName("CostumersServiceImplemete")
public class CostumersServiceTest  extends AplicationConfingTest {
    @MockBean
   public CostumersRepository costumersRepository;
    @Autowired
   public CostumersService costumersService;


    @Test
    public void findTest(){

        this.costumersService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.costumersService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Carteira de motorista não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.costumersService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.costumersService.pages(pageRequest);


    }

    @Test
    public void saveTest(){

        var costumers = new Costumers(null,"lucas trindade",new Date(),"lucasDevJava@gmail.com","67365501505","adress","(81)9 812322-7979",null,null,null);

        this.costumersService.save(costumers);


    }
    @Test
    public void saveTestErro(){

        var costumers = new Costumers(null,"lucas trindade",new Date(),"lucasDevJava@gmail.com","28821765240","adress","(81)9 812322-7979",null,null,null);
        try {
        this.costumersService.save(costumers);
        Assertions.fail("Erro. Não foi gerado o erro UniqueFieldException.");
        }catch (UniqueFieldException err){
            Assertions.assertEquals(err.getMessage(),"O Carteira de motorista já existe no sistema. Por favor, tente outro");
        }
    }
    @Test
    public  void deliteTest(){

        this.costumersService.delete(12);

    }

    @BeforeEach
    public void setup() {
        var costumers = new Costumers(null,"lucas trindade",new Date(),"lucasDevJava@gmail.com","28821765240","adress","(81)9 812322-7979",null,null,null);

        Mockito.when( this.costumersRepository.findByDriver_license("28821765240")).thenReturn(costumers);
        Mockito.when( this.costumersRepository.findById(12)).thenReturn(Optional.of(costumers));
        Mockito.when( this.costumersRepository.findById(13)).thenReturn(Optional.empty());

    }
}
