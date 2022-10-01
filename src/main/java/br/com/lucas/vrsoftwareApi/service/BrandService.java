package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BrandService {

      Brand find(Integer id);
      List<Brand> findAll();
      Page<Brand> pages(PageRequest pageRequest);
      Brand save(Brand brand);
      void delete(Integer id);

}
