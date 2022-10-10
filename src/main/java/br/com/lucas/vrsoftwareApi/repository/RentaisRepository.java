package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RentaisRepository extends JpaRepository<Rentais,Integer>, PagingAndSortingRepository<Rentais,Integer> {
    @Query(value = " select  * FROM rentais rent WHERE rent.car_id = ?3 and ((rent.start_date between ?1 and ?2 ) or (rent.end_date between ?1 and ?2 ))", nativeQuery = true)
    List<Rentais> checkAvailability(LocalDateTime start_date, LocalDateTime end_date,Integer car);


    @Query(value = "select  * FROM rentais rent WHERE rent.id = ?3 and rent.car_id = ?4 and ((rent.start_date between ?1 and ?2 ) or (rent.end_date between ?1 and ?2 ))", nativeQuery = true)
    List<Rentais> checkAvailabilityNoId(LocalDateTime start_date, LocalDateTime end_date,Integer id,Integer car);
}
