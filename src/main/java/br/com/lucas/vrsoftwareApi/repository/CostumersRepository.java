package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Costumers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumersRepository extends JpaRepository<Costumers,Integer>, PagingAndSortingRepository<Costumers,Integer> {
    Costumers findByDriver_license(String driver_license);
}
