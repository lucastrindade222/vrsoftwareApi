package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.CostumersNew;
import br.com.lucas.vrsoftwareApi.dto.CostumersUpDate;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.service.CostumersService;
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
@RequestMapping("costumers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CostumersResource {

    @Autowired
    private CostumersService costumersService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Costumers>> findAll(){
        var costumersList = this.costumersService.findAll();
        return  ResponseEntity.ok().body(costumersList);
    }
    @GetMapping("/find")
    public  ResponseEntity<Costumers> findById(@RequestParam(value = "id")  Integer id){
        var costumers = this.costumersService.find(id);
        return  ResponseEntity.ok().body(costumers);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Costumers>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var costumersPage = this.costumersService.pages(pagesRequest);
        return  ResponseEntity.ok().body(costumersPage);
    }
    @PostMapping
    public ResponseEntity<Costumers> save(@Validated @RequestBody CostumersNew costumersNew){
        var costumers = this.modelMapper.map(costumersNew, Costumers.class);
        costumers = this.costumersService.save(costumers);
        return ResponseEntity.status(HttpStatus.CREATED).body(costumers);
    }
    @PutMapping
    public ResponseEntity<Costumers> Update(@Validated @RequestBody CostumersUpDate costumersUpDate){

        var  costumers = this.costumersService.upData(costumersUpDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(costumers);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        this.costumersService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
