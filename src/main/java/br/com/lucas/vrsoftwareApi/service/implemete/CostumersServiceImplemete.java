package br.com.lucas.vrsoftwareApi.service.implemete;


import br.com.lucas.vrsoftwareApi.dto.CostumersUpDate;
import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.repository.CostumersRepository;
import br.com.lucas.vrsoftwareApi.service.CostumersService;
import br.com.lucas.vrsoftwareApi.service.exception.ObjectNotFoundException;
import br.com.lucas.vrsoftwareApi.service.exception.UniqueFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CostumersServiceImplemete implements CostumersService {
    @Autowired
    private CostumersRepository costumersRepository;

    @Override
    public Costumers find(Integer id) {
        Optional<Costumers> costumers = this.costumersRepository.findById(id);
        return costumers.orElseThrow(()->new ObjectNotFoundException("Carteira de motorista não encontrada com o id:"+id));
    }

    @Override
    public List<Costumers> findAll() {
        return this.costumersRepository.findAll();
    }

    @Override
    public Costumers findByDriver_license(String driver_license) {
        return this.costumersRepository.findByDriver_license(driver_license);
    }

    @Override
    public Page<Costumers> pages(PageRequest pageRequest) {
        return this.costumersRepository.findAll(pageRequest);
    }

    @Override
    public Costumers save(Costumers costumers) {

        if(this.costumersRepository.findByDriver_license(costumers.getDriver_license()) != null){
            throw new UniqueFieldException("O Carteira de motorista já existe no sistema. Por favor, tente outro");
        }
        costumers.setCreated_at(new Date());
        return this.costumersRepository.save(costumers);
    }

    @Override
    public Costumers upData(CostumersUpDate costumersUpDate) {

        var costumersDB = this.find(costumersUpDate.getId());
        this.from(costumersDB,costumersUpDate);
        return this.costumersRepository.save(costumersDB);
    }

    @Override
    public void from(Costumers costumersDB, CostumersUpDate costumersUpDate) {

        if(costumersUpDate.getName() != null ){
            costumersDB.setName(costumersUpDate.getName());
        }
        if(costumersUpDate.getEmail() != null ){
            costumersDB.setEmail(costumersUpDate.getEmail());
        }
        if(costumersUpDate.getAdress() != null ){
            costumersDB.setAdress(costumersUpDate.getAdress());
        }
        if(costumersUpDate.getPhone_number() != null ){
            costumersDB.setPhone_number(costumersUpDate.getPhone_number());
        }
        if(costumersUpDate.getBirth_date() != null){
            costumersDB.setBirth_date(costumersUpDate.getBirth_date());
        }

    }


    @Override
    public void delete(Integer id) {
        Costumers costumers = this.find(id);
        this.costumersRepository.delete(costumers);
    }
}
