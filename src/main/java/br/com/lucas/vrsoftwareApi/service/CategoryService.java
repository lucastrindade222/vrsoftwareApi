package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {

    Category find(Integer id);
    List<Category> findAll();
    Page<Category> pages(PageRequest pageRequest);
    Category save(Category category);
    void delete(Integer id);
}
