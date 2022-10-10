package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.BrandNew;
import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.CheckById;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import br.com.lucas.vrsoftwareApi.service.RentaisService;
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
@RequestMapping("rentais")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentaisResource {

    @Autowired
    private RentaisService rentaisService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Rentais>> findAll(){
        var brandList = this.rentaisService.findAll();
        return  ResponseEntity.ok().body(brandList);
    }
    @GetMapping("/find")
    public  ResponseEntity<Rentais> findById(@RequestParam(value = "id")  Integer id){
        var brand = this.rentaisService.find(id);
        return  ResponseEntity.ok().body(brand);
    }
    @GetMapping("/check")
    public  ResponseEntity<CheckById> checkById(@RequestParam(value = "id")  Integer id){
        var check = this.rentaisService.checkById(id);
        return  ResponseEntity.ok().body(new CheckById(check));
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Rentais>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var brandPages = this.rentaisService.pages(pagesRequest);
        return  ResponseEntity.ok().body(brandPages);
    }
    @PostMapping
    public ResponseEntity<Rentais> save(@Validated @RequestBody RentaisNew rentaisNew){
        var  rentais = this.rentaisService.save(rentaisNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(rentais);
    }
    @PostMapping("/checkAvailability")
    public ResponseEntity<Rentais> checkAvailability(@Validated @RequestBody CheckAvailability checkAvailability){
        this.rentaisService.checkAvailability(checkAvailability);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/extend-period")
    public ResponseEntity<Rentais> extendTheLeasePeriod(@RequestParam(value = "rentai_id") Integer rentai_id,@RequestParam(value = "plus_days") Long plus_days){
        var rentais = this.rentaisService.extendTheLeasePeriod(rentai_id,plus_days);
        return ResponseEntity.ok().body(rentais);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        this.rentaisService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
