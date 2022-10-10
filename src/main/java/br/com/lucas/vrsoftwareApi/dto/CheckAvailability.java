package br.com.lucas.vrsoftwareApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAvailability {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate start_date;
    private Long numberOfDaysRentals;
    private Integer  id;

}
