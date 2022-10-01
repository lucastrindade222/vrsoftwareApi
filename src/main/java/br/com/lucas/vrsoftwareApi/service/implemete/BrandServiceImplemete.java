package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.repository.BrandRepository;
import br.com.lucas.vrsoftwareApi.service.BrandService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImplemete implements BrandService {
    @Autowired
    public BrandRepository brandRepository;

    @Override
    public Brand find(Integer id) {
        Optional<Brand> brand = this.brandRepository.findById(id);
        return brand.orElseThrow(()->new ObjectNotFoundException("Marca não encontrada com o id:"+id));
    }

    @Override
    public List<Brand> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Page<Brand> pages(  PageRequest pageRequest) {
        return this.brandRepository.findAll(pageRequest);
    }

    @Override
    public Brand save(Brand brand) {
        brand.setCreated_at(new Date());

        brand = this.brandRepository.save(brand);
        return brand;
    }

    @Override
    public void delete(Integer id) {
      Brand brand = this.find(id);
      this.brandRepository.delete(brand);

    }
}
