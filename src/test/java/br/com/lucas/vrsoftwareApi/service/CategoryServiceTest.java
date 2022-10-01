package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.config.AplicationConfingTest;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.repository.CategoryRepository;
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

@DisplayName("CategoryServiceImplemente")
public class CategoryServiceTest  extends AplicationConfingTest {
    @MockBean
    public CategoryRepository categoryRepository;
    @Autowired
    public CategoryService categoryService;

    @Test
    public void findTest(){

        this.categoryService.find(12);

    }
    @Test
    public void findErroTest(){
        try {
            this.categoryService.find(13);
            Assertions.fail("Erro. Não foi gerado o erro ObjectNotFoundException.");
        }catch (ObjectNotFoundException err){
            Assertions.assertEquals(err.getMessage(),"Categoria não encontrada com o id:13");
        }


    }
    @Test
    public void findAllTest(){

        this.categoryService.findAll();

    }
    @Test
    public void pagesTest(){
        Integer page = 0;
        Integer limesPerPage = 20;
        String orderBy = "name";
        String direction = "DESC";
        var pageRequest =  UTILS.now().pages(page,limesPerPage,orderBy,direction);
        this.categoryService.pages(pageRequest);


    }

    @Test
    public void saveTest(){
        String name = "sedã compacto";
        String discretion = "O carro sedan é aquele em que a carroceria pode ser dividida em três partes, sendo a última delas a traseira, que tem um volume bem maior e mais projetada do que em outros modelos de veículos.";
        var category = new Category(null,name,discretion,null,null);

        this.categoryService.save(category);

    }
    @Test
    public  void deliteTest(){

        this.categoryService.delete(12);

    }

    @BeforeEach
    public void setup() {
        String name = "sedã compacto";
        String discretion = "O carro sedan é aquele em que a carroceria pode ser dividida em três partes, sendo a última delas a traseira, que tem um volume bem maior e mais projetada do que em outros modelos de veículos.";
        var category = new Category(null,name,discretion,null,null);

        Mockito.when( this.categoryRepository.findById(12)).thenReturn(Optional.of(category));
        Mockito.when( this.categoryRepository.findById(13)).thenReturn(Optional.empty());

    }
}
