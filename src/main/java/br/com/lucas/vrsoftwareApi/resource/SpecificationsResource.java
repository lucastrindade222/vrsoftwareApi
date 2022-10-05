package br.com.lucas.vrsoftwareApi.resource;


import br.com.lucas.vrsoftwareApi.dto.SpecificationsFindAll;
import br.com.lucas.vrsoftwareApi.dto.SpecificationsNew;
import br.com.lucas.vrsoftwareApi.model.Specifications;
import br.com.lucas.vrsoftwareApi.service.SpecificationsService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("specifications")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SpecificationsResource {

    @Autowired
    private SpecificationsService specificationsService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SpecificationsFindAll>> findAll(){
        var specificationsList = this.specificationsService.findAll();

        List<SpecificationsFindAll> specificationsFindAlls = specificationsList
                .stream()
                .map(obj -> this.modelMapper.map(obj,SpecificationsFindAll.class)).
                collect(Collectors.toList());

        return  ResponseEntity.ok().body(specificationsFindAlls);
    }
    @GetMapping("/find")
    public  ResponseEntity<Specifications> findById(@RequestParam(value = "id")  Integer id){
        var specifications = this.specificationsService.find(id);
        return  ResponseEntity.ok().body(specifications);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Specifications>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);

        var specificationsPage = this.specificationsService.pages(pagesRequest);


        return  ResponseEntity.ok().body(specificationsPage);
    }
    @PostMapping
    public ResponseEntity<Specifications> save(@Validated @RequestBody SpecificationsNew specificationsNew){
        var specifications = this.modelMapper.map(specificationsNew,Specifications.class);
        specifications = this.specificationsService.save(specifications);
        return ResponseEntity.status(HttpStatus.CREATED).body(specifications);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        this.specificationsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
