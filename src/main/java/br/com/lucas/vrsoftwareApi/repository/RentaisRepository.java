package br.com.lucas.vrsoftwareApi.repository;

import br.com.lucas.vrsoftwareApi.model.Rentais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface RentaisRepository extends JpaRepository<Rentais,Integer>, PagingAndSortingRepository<Rentais,Integer> {
    @Query(value = " SELECT * FROM compra  WHERE compra.vendedor_id = ?1 and compra.status_da_compra = 0", nativeQuery = true)
    Rentais checkAvailability(  LocalDateTime start_date,  LocalDateTime end_date,   Integer cra);
}
