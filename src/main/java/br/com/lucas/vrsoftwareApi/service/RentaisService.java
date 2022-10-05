package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Cras;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RentaisService {
    Rentais find(Integer id);
    List<Rentais> findAll();
    Page<Rentais> pages(PageRequest pageRequest);
    Rentais save(RentaisNew rentaisNew);
    Rentais fromRentaisNewToRentais(RentaisNew rentaisNew);
    BigDecimal calculateTotal(BigDecimal daily_rate,Long days);
    Rentais extendTheLeasePeriod(Integer rentai_id, Long plus_days );
    void checkAvailabilityNoId(LocalDateTime start_date, LocalDateTime end_date,Integer id );
    void delete(Integer id);
}
