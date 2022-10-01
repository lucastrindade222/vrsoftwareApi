package br.com.lucas.vrsoftwareApi.service.implemete;

import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import br.com.lucas.vrsoftwareApi.repository.RentaisRepository;
import br.com.lucas.vrsoftwareApi.service.CostumersService;
import br.com.lucas.vrsoftwareApi.service.CrasService;
import br.com.lucas.vrsoftwareApi.service.RentaisService;
import br.com.lucas.vrsoftwareApi.service.exception.DataIntegrityException;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentaisServiceImplemete implements RentaisService {
    @Autowired
    public RentaisRepository rentaisRepository;
    @Autowired
    public CostumersService costumersService;
    @Autowired
    public CrasService crasService;

    @Override
    public Rentais find(Integer id) {
        Optional<Rentais> rentais = this.rentaisRepository.findById(id);
        return rentais.orElseThrow(()->new ObjectNotFoundException("Aluguel não encontrada com o id:"+id));
    }

    @Override
    public List<Rentais> findAll() {
        return  this.rentaisRepository.findAll();
    }

    @Override
    public Page<Rentais> pages(PageRequest pageRequest) {
        return  this.rentaisRepository.findAll(pageRequest);
    }

    @Override
    public Rentais save(RentaisNew rentaisNew) {
      var rentais = this.fromRentaisNewToRentais(rentaisNew);
      return  this.rentaisRepository.save(rentais);
    }
    @Override
    public Rentais fromRentaisNewToRentais(RentaisNew rentaisNew){
        Costumers costumer = this.costumersService.find(rentaisNew.getCostumer());
        Cras cras = this.crasService.find(rentaisNew.getCras());
        return Rentais.builder()
                .costumer(costumer)
                .cras(cras)
                .total(rentaisNew.getTotal())
                .created_at(new Date())
                .start_date(rentaisNew.getStart_date())
                .end_date(rentaisNew.getStart_date().plusDays(rentaisNew.getNumberOfDaysRentals()))
                .build();

    }

    @Override
    public void checkAvailability(CheckAvailability checkAvailability) {
         var check = this.rentaisRepository.checkAvailability(checkAvailability.getStart_date(),
                 checkAvailability.getStart_date().plusDays(checkAvailability.getNumberOfDaysRentals()),checkAvailability.getCra());
       if(check != null){
       throw  new DataIntegrityException("Esta data o veiculo esta reservado.");
       }
    }


    @Override
    public void delete(Integer id) {
        Rentais rentais = this.find(id);
        this.rentaisRepository.delete(rentais);
    }
}
