package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.dto.CrasNew;
import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Category;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.CrasRepository;
import br.com.lucas.vrsoftwareApi.service.BrandService;
import br.com.lucas.vrsoftwareApi.service.CategoryService;
import br.com.lucas.vrsoftwareApi.service.CrasService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CrasServiceImplemente implements CrasService {
    @Autowired
    public CrasRepository crasRepository;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public BrandService brandService;

    @Override
    public Cras find(Integer id) {

        Optional<Cras> cras = this.crasRepository.findById(id);
        return cras.orElseThrow(()->new ObjectNotFoundException("Carros não encontrada com o id:"+id));
    }

    @Override
    public List<Cras> findAll() {
        return this.crasRepository.findAll();
    }

    @Override
    public Page<Cras> pages(PageRequest pageRequest) {
        return this.crasRepository.findAll(pageRequest);

    }

    @Override
    public Cras save(CrasNew crasNew) {
        var cras=  fromCrasNewToCras(crasNew);
        return this.crasRepository.save(cras);
    }

    @Override
    public Cras fromCrasNewToCras(CrasNew crasNew) {
        Brand brand = this.brandService.find(crasNew.getBrand());
        Category category = this.categoryService.find(crasNew.getCategory());


        return  Cras.builder()
                .brand(brand)
                .category(category)
                .name(crasNew.getName())
                .description(crasNew.getDescription())
                .color(crasNew.getColor())
                .available(true)
                .created_at(new Date())
                .daily_rate(crasNew.getDaily_rate())
                .build();

    }

    @Override
    public void delete(Integer id) {
    var cras = this.find(id);
    this.crasRepository.delete(cras);
    }
}
