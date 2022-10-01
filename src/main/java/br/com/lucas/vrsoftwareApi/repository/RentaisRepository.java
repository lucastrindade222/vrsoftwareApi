package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Rentais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface RentaisRepository extends JpaRepository<Rentais,Integer>, PagingAndSortingRepository<Rentais,Integer> {
    @Query(value = " SELECT id, created_at, end_date, start_date, total, update_at, costumer_id, car_id\n" +
            "FROM public.rentais WHERE  BETWEEN ?1 AND ?2 AND car_id = ?3 ", nativeQuery = true)
    Rentais checkAvailability(LocalDateTime start_date,LocalDateTime end_date,Integer cra);
}
