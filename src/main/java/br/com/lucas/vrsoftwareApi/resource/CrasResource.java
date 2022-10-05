package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.dto.*;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.service.CrasService;
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
@RequestMapping("cras")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CrasResource {
    @Autowired
    private CrasService crasService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Cras>> findAll(){
        var crasList = this.crasService.findAll();
        return  ResponseEntity.ok().body(crasList);
    }
    @GetMapping("/find")
    public  ResponseEntity<Cras> findById(@RequestParam(value = "id")  Integer id){
        var cras = this.crasService.find(id);
        return  ResponseEntity.ok().body(cras);
    }
    @GetMapping("/page")
    public  ResponseEntity<Page<Cras>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        PageRequest pagesRequest = UTILS.now().pages(page, linesPerPage, orderBy, direction);
        var crasPage = this.crasService.pages(pagesRequest);

        return  ResponseEntity.ok().body(crasPage);
    }

    @GetMapping("/consult")
    public  ResponseEntity<ConsultTotal> consultTotalValue(@RequestParam(value = "car_id")  Integer car_id,@RequestParam(value = "days")  Long days){
        var totalValue = this.crasService.consultTotalValue(car_id,days);
        return  ResponseEntity.ok().body(new ConsultTotal(totalValue));
    }
    @PostMapping("/check")
    public ResponseEntity<List<Cras>> availableCars(@Validated @RequestBody CheckAvailability checkAvailability){
        var  cras = this.crasService.availableCars(checkAvailability);
        return ResponseEntity.ok().body(cras);
    }

    @PostMapping
    public ResponseEntity<Cras> save(@Validated @RequestBody CrasNew crasNew){
        var  cras = this.crasService.save(crasNew);
        return ResponseEntity.status(HttpStatus.CREATED).body(cras);
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "id")  Integer id){
        this.crasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
