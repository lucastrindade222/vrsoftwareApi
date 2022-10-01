package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.model.Specifications;
import br.com.lucas.vrsoftwareApi.repository.SpecificationsRepository;
import br.com.lucas.vrsoftwareApi.service.SpecificationsService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationsServiceImplemete implements SpecificationsService {
    @Autowired
    public SpecificationsRepository specificationsRepository;
    @Override
    public Specifications find(Integer id) {
        Optional<Specifications> specifications = this.specificationsRepository.findById(id);
        return specifications.orElseThrow(()-> new ObjectNotFoundException("Marca não encontrada com o id:"+id));
    }

    @Override
    public List<Specifications> findAll() {
        return this.specificationsRepository.findAll();
    }

    @Override
    public Page<Specifications> pages(PageRequest pageRequest) {
        return this.specificationsRepository.findAll(pageRequest);
    }

    @Override
    public Specifications save(Specifications specifications) {

        return this.specificationsRepository.save(specifications);
    }

    @Override
    public void delete(Integer id) {
     var specifications = this.find(id);
     this.specificationsRepository.delete(specifications);
    }
}
