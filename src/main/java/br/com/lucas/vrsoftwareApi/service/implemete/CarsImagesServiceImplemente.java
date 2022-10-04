package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.model.CarsImages;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.repository.CarsImagesRepository;

import br.com.lucas.vrsoftwareApi.service.CarsImagesService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarsImagesServiceImplemente implements CarsImagesService {

    @Autowired
    private CarsImagesRepository carsImagesRepository;

    @Override
    public CarsImages find(Integer id) {
        Optional<CarsImages> image = this.carsImagesRepository.findById(id);
        return image.orElseThrow(()->new ObjectNotFoundException("Image não encontrada com o id:"+id));
    }

    @Override
    public List<CarsImages> findAll() {
        return this.carsImagesRepository.findAll();
    }


    @Override
    public Page<CarsImages> pages(PageRequest pageRequest) {
        return this.carsImagesRepository.findAll(pageRequest);
    }

    @Override
    public CarsImages save(Integer car_id, byte[] file)
    {



            Cras cra = new Cras();
            var carsImages = new  CarsImages(null,new Date(),cra);
            return this.carsImagesRepository.save(carsImages);


    }

    @Override
    public void delete(Integer id) {
        CarsImages carsImages = this.find(id);
        this.carsImagesRepository.delete(carsImages);
    }
}
