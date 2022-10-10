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


import java.math.BigDecimal;
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
    public boolean checkById(Integer id) {
       var rentais = this.find(id);

       return LocalDateTime.now().isBefore( rentais.getStart_date());
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

        var list= this.rentaisRepository.checkAvailability(rentaisNew.getStart_date().atStartOfDay(),(rentaisNew.getStart_date().atStartOfDay().plusDays(rentaisNew.getNumberOfDaysRentals())),rentaisNew.getCras());
        System.out.println(list.size());
        if(list.size()>0){
            throw new DataIntegrityException("Conflito de Datas.");
        }

        Costumers costumer = this.costumersService.find(rentaisNew.getCostumer());
        Cras cras = this.crasService.find(rentaisNew.getCras());
        BigDecimal total = this.calculateTotal(cras.getDaily_rate(),rentaisNew.getNumberOfDaysRentals());
        return Rentais.builder()
                .costumer(costumer)
                .cras(cras)
                .total(total)
                .created_at(new Date())
                .start_date(rentaisNew.getStart_date().atStartOfDay())
                .end_date(rentaisNew.getStart_date().atStartOfDay().plusDays(rentaisNew.getNumberOfDaysRentals()))
                .build();

    }

    @Override
    public BigDecimal calculateTotal(BigDecimal daily_rate, Long days) {
       var total = daily_rate.multiply(new BigDecimal(days));
        return  total;
    }

    @Override
    public Rentais extendTheLeasePeriod(Integer rentai_id, Long plus_days) {
        var rentais = this.find(rentai_id);

      LocalDateTime newEnd = rentais.getEnd_date().plusDays(plus_days);
      BigDecimal calculatedTotal = this.calculateTotal(rentais.getCras().getDaily_rate(),plus_days);
      BigDecimal newTotal = calculatedTotal.add(rentais.getTotal());
      this.checkAvailabilityNoId(rentais.getStart_date(),newEnd,rentai_id,rentais.getCras().getId());
      rentais.setTotal(newTotal);
      rentais.setUpdate_at(new Date());
      return this.rentaisRepository.save(rentais);
    }

    @Override
    public void checkAvailabilityNoId(LocalDateTime start_date, LocalDateTime end_date, Integer id,Integer car) {
        List<Rentais> check = this.rentaisRepository.checkAvailabilityNoId(start_date,end_date,id,car);

        if(check.size()>0){
            throw new DataIntegrityException("Conflito de Data");
        }

    }

    @Override
    public void checkAvailability(CheckAvailability checkAvailability) {
        var list= this.rentaisRepository.checkAvailability(checkAvailability.getStart_date().atStartOfDay(),(checkAvailability.getStart_date().atStartOfDay().plusDays(checkAvailability.getNumberOfDaysRentals())),checkAvailability.getId());
        System.out.println(list.size());
        if(list.size()>0){
            throw new DataIntegrityException("Conflito de Datas.");
        }

    }


    @Override
    public void delete(Integer id) {
        Rentais rentais = this.find(id);
        var check= LocalDateTime.now().isAfter(rentais.getStart_date());
        if (check){
            throw new DataIntegrityException("Não pode apagar pois não esta no passado");
        }
        this.rentaisRepository.delete(rentais);
    }
}
