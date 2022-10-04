package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Costumers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CostumersRepository extends JpaRepository<Costumers,Integer>, PagingAndSortingRepository<Costumers,Integer> {

    @Query(value = " SELECT * FROM costumers where driver_license  like ':driver_license'; ", nativeQuery = true)
    Costumers findByDriver_license(@Param("driver_license")String driver_license);


}