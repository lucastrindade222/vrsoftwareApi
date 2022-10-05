package br.com.lucas.vrsoftwareApi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentaisNew {


    private Integer cras;
    private Integer costumer;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate start_date;
    private long numberOfDaysRentals;


}
