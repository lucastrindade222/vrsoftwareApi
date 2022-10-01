package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface CarsImagesService {

    CarsImages find(Integer id);
    List<CarsImages> findAll();
    Page<CarsImages> pages(PageRequest pageRequest);
    CarsImages save(Integer car_id,byte[] file);
    void delete(Integer id);


}
