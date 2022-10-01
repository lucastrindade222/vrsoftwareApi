package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.RentaisNew;
import br.com.lucas.vrsoftwareApi.model.Rentais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RentaisService {
    Rentais find(Integer id);
    List<Rentais> findAll();
    Page<Rentais> pages(PageRequest pageRequest);
    Rentais save(RentaisNew rentaisNew);
    Rentais fromRentaisNewToRentais(RentaisNew rentaisNew);
    void checkAvailability(CheckAvailability checkAvailability);
    void delete(Integer id);
}
