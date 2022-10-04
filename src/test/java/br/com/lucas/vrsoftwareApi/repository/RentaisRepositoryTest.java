//package br.com.lucas.vrsoftwareApi.repository;
//
//import br.com.lucas.vrsoftwareApi.model.Brand;
//import br.com.lucas.vrsoftwareApi.model.Category;
//import br.com.lucas.vrsoftwareApi.model.Cras;
//import br.com.lucas.vrsoftwareApi.model.Rentais;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//
//
//@DataJpaTest
//public class RentaisRepositoryTest {
//    @Autowired
//    private TestEntityManager entityManager;
//    @Autowired
//    private RentaisRepository rentaisRepository;
//
//    @Test
//    public void checkAvailability(){
//        var brand = new Brand();
//
//
//        String name = "sedã compacto";
//        String discretion = "O carro sedan é aquele em que a carroceria pode ser dividida em três partes, sendo a última delas a traseira, que tem um volume bem maior e mais projetada do que em outros modelos de veículos.";
//        var category = new Category(null,name,discretion,null,null);
//
//
//
//        String description ="Esportivo, clássico e voltado para o futuro: o lançamento do BMW F30 em 14 de outubro de 2011 na fábrica da BMW em Munique, revelou os caminhos inovadores que a Bayerische Motoren Werke explorou nesta sexta versão do BMW Série 3. A aparência marcante do BMW F30 se baseia na atual linha de";
//        String license_plate="NAY-2083";
//        var daily_rate = new BigDecimal("800.00");
//        boolean available = true;
//        String color = "Red";
//
//        var car = new Cras(12,"F34",description,daily_rate,available,license_plate,brand,category,color,new Date(),null,null);
//
//        this.entityManager.persist(Rentais.builder()
//
//                 .start_date(LocalDateTime.now())
//                 .end_date(LocalDateTime.now().plusDays(14))
//                 .created_at(new Date())
//
//                 .total(1)
//                 .build());
//
//
//
//       var rentai = this.rentaisRepository.checkAvailability(LocalDateTime.now(),LocalDateTime.now().plusDays(14),22);
//        Assertions.assertNotNull(rentai,"Objeto null");
//    }
//}
