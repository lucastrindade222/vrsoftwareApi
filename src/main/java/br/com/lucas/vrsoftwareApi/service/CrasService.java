package br.com.lucas.vrsoftwareApi.service;

import br.com.lucas.vrsoftwareApi.dto.CheckAvailability;
import br.com.lucas.vrsoftwareApi.dto.CrasNew;
import br.com.lucas.vrsoftwareApi.model.Cras;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CrasService {

    Cras find(Integer id);
    List<Cras> findAll();
    Page<Cras> pages(PageRequest pageRequest);
    List<Cras> availableCars(CheckAvailability checkAvailability);
    BigDecimal consultTotalValue(Integer car_id, Long days);
    Cras save(CrasNew crasNew);
    Cras fromCrasNewToCras(CrasNew crasNew);
    void delete(Integer id);
}
