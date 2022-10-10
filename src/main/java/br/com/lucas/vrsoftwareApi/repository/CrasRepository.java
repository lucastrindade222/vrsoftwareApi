package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Cras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CrasRepository extends JpaRepository<Cras,Integer>, PagingAndSortingRepository<Cras,Integer> {

    @Query(value =  "select * from cras c where c.id not in(select  rent.car_id FROM rentais rent WHERE (rent.start_date between ?1 and ?2 ) or (rent.end_date between ?1 and ?2 ) )", nativeQuery = true)
    List<Cras> availableCars(LocalDateTime start_date, LocalDateTime end_date);
}
