package br.com.lucas.vrsoftwareApi.resource;


import br.com.lucas.vrsoftwareApi.dto.CategoryNew;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.service.CategoryService;
import br.com.lucas.vrsoftwareApi.utils.UTILS;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryResource {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        var categoryList = categoryService.findAll();
        return  ResponseEntity.ok().body(categoryList);
    }
    @GetMapping("/find")
    public  ResponseEntity<Category> findById(@RequestParam(value = "id")  Integer id){
        var category = categoryService.find(id);
        return  ResponseEntity.ok().body(category);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Category>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var categoryPages = categoryService.pages(pagesRequest);
        return  ResponseEntity.ok().body(categoryPages);
    }
    @PostMapping
    public ResponseEntity<Category> save(@Validated @RequestBody CategoryNew categoryNew){
        var category = this.modelMapper.map(categoryNew,Category.class);
        category = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
