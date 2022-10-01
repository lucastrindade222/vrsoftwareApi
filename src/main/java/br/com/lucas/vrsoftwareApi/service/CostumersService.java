package br.com.lucas.vrsoftwareApi.service;


import br.com.lucas.vrsoftwareApi.model.Costumers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CostumersService {
    Costumers find(Integer id);
    List<Costumers> findAll();

    Costumers findByDriver_license(String driver_license);
    Page<Costumers> pages(PageRequest pageRequest);
    Costumers save(Costumers costumers);
    void delete(Integer id);
}
