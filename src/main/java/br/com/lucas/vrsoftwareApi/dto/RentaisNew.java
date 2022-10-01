package br.com.lucas.vrsoftwareApi.dto;

import br.com.lucas.vrsoftwareApi.model.Costumers;
import br.com.lucas.vrsoftwareApi.model.Cras;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentaisNew {


    private Integer cras;
    private Integer costumer;
    private LocalDateTime start_date;
    private long numberOfDaysRentals;
    private Integer total;
    private Date created_at;
    private Date update_at;
}
