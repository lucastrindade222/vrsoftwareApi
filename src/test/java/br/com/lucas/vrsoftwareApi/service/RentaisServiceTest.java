package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import br.com.lucas.vrsoftwareApi.repository.CostumersRepository;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.repository.RentaisRepository;
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
import java.util.Optional;

@DisplayName("RentaisServiceImplemete")
public class RentaisServiceTest extends AplicationConfingTest {
    @Autowired
    public RentaisService rentaisService;
    @MockBean
    public RentaisRepository rentaisRepository;
    @MockBean
    public CrasRepository crasRepository;
    @MockBean
    public CostumersRepository costumersRepository;

    @Test
    public void findTest(){

        this.rentaisService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.rentaisService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Aluguel não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.rentaisService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.rentaisService.pages(pageRequest);


    }

    @Test
    public void saveTest(){
        var rentais = new RentaisNew(1,1, LocalDate.now(),5);


        this.rentaisService.save(rentais);

    }
    @Test
    public void calculateTotal(){

       BigDecimal total=  this.rentaisService.calculateTotal(new BigDecimal("10"),2l);

      Assertions.assertEquals(total,new BigDecimal("20"));
    }
   @Test
   public void extendTheLeasePeriod(){

         this.rentaisService.extendTheLeasePeriod(12,10l);

   }


    @Test
    public  void deliteTest(){

        this.rentaisService.delete(12);

    }
    @Test
    public  void deliteErrTest(){
        try {
            this.rentaisService.delete(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Aluguel não encontrada com o id:13");
        }
    }



    @BeforeEach
    public void setup() {
        var cra = new Cras();
        cra.setDaily_rate(new BigDecimal("100"));
        Mockito.when( this.crasRepository.findById(1)).thenReturn(Optional.of(cra));

        var rentais = new Rentais();
        rentais.setStart_date(LocalDateTime.now().plusDays(1));
        rentais.setEnd_date(LocalDateTime.now().plusDays(5));
        rentais.setTotal(new BigDecimal("100"));
        rentais.setCras(cra);

        Mockito.when( this.rentaisRepository.findById(12)).thenReturn(Optional.of(rentais));
        Mockito.when( this.rentaisRepository.findById(13)).thenReturn(Optional.empty());



       var costumers = new Costumers();
       Mockito.when( this.costumersRepository.findById(1)).thenReturn(Optional.of(costumers));




    }
    
}
