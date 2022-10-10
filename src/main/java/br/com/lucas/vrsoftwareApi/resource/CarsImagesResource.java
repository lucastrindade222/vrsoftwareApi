package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.CarsImagesNew;
import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.service.CarsImagesService;
import br.com.lucas.vrsoftwareApi.utils.UTILS;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("cars-images")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CarsImagesResource {
    @Autowired
    private CarsImagesService carsImagesService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CarsImages>> findAll(){
        var carsImages = this.carsImagesService.findAll();
        return  ResponseEntity.ok().body(carsImages);
    }
    @GetMapping("/find")
    public  ResponseEntity<CarsImages> findById(@RequestParam(value = "id")  Integer id){
        var carsImages = this.carsImagesService.find(id);
        return  ResponseEntity.ok().body(carsImages);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<CarsImages>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var carsImages = this.carsImagesService.pages(pagesRequest);
        return  ResponseEntity.ok().body(carsImages);
    }
    @PostMapping
    public ResponseEntity<CarsImages> save(@Validated @RequestBody CarsImagesNew carsImagesNew) throws IOException {


        var carsImages = this.carsImagesService.save(carsImagesNew.getId(), carsImagesNew.getCro());
        return ResponseEntity.status(HttpStatus.CREATED).body(carsImages);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CarsImages> saveParam(@RequestParam(name = "file") MultipartFile file,
                                           @PathVariable Integer id) throws IOException {

       var carsImages = this.carsImagesService.save(id,file.getBytes());
        return ResponseEntity.status(HttpStatus.CREATED).body(carsImages);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        this.carsImagesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
