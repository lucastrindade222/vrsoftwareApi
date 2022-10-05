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
    @Query(value = " select  *  FROM rentais rent where (rent.start_date >= ?1 and rent .start_date <= ?1  ) or (rent.start_date >= ?2 and rent .start_date <= ?2) and rent.car_id = ?3", nativeQuery = true)
    List<Rentais> checkAvailability(LocalDateTime start_date, LocalDateTime end_date,Integer car);


    @Query(value = " select *  FROM rentais rent where  rent.id <> ?3 and (rent.start_date >= ?1 and rent .start_date <= ?1  ) or (rent.start_date >= ?2 and rent .start_date <= ?2) and rent.id = ?3", nativeQuery = true)
    List<Rentais> checkAvailabilityNoId(LocalDateTime start_date, LocalDateTime end_date,Integer id);
}
