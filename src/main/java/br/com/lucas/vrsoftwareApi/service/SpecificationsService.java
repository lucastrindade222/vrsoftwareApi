package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Specifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SpecificationsService {
    Specifications find(Integer id);
    List<Specifications> findAll();
    Page<Specifications> pages(PageRequest pageRequest);
    Specifications save(Specifications specifications);
    void delete(Integer id);

}
