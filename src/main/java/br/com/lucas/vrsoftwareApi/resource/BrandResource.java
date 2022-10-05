package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.BrandNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.service.BrandService;
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
@RequestMapping("brand")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BrandResource {
@Autowired
private BrandService brandService;


  @Autowired
  private ModelMapper modelMapper;

    @GetMapping
    public  ResponseEntity<List<Brand>> findAll(){
        var brandList = this.brandService.findAll();
        return  ResponseEntity.ok().body(brandList);
    }
    @GetMapping("/find")
    public  ResponseEntity<Brand> findById(@RequestParam(value = "id")  Integer id){
        var brand = this.brandService.find(id);
        return  ResponseEntity.ok().body(brand);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Brand>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var brandPages = this.brandService.pages(pagesRequest);
        return  ResponseEntity.ok().body(brandPages);
    }
    @PostMapping
    public ResponseEntity<Brand> save(@Validated @RequestBody BrandNew brandNew){
        var brand = this.modelMapper.map(brandNew,Brand.class);
        brand = this.brandService.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
       this.brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
