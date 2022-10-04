package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Costumers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

@DataJpaTest
public class CostumersRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CostumersRepository costumersRepository;
    @Test
    public void findByDriver_license(){


        var costumers = new Costumers(2,"lucas trindade",new Date(),"lucasDevJava@gmail.com","28821765240","adress","(81)9 812322-7979",new Date(),null,null);

        this.entityManager.persist(costumers);
        this.costumersRepository.findByDriver_license("28821765240");

    }


}
