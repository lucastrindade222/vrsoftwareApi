package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.repository.CategoryRepository;
import br.com.lucas.vrsoftwareApi.service.CategoryService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImplemente implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category find(Integer id) {
        Optional<Category> category = this.categoryRepository.findById(id);
        return category.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada com o id:"+id));
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> pages(PageRequest pageRequest) {
        return this.categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category save(Category category) {
        category.setCreated_at(new Date());
        return this.categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        Category  category = this.find(id);
        this.categoryRepository.delete(category);
    }
}
