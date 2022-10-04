package br.com.lucas.vrsoftwareApi.resource;

import br.com.lucas.vrsoftwareApi.service.CostumersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("costumers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CostumersResource {
@Autowired
private CostumersService costumersService;



}
