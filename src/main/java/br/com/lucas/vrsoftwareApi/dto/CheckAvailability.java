package br.com.lucas.vrsoftwareApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAvailability {
    private LocalDateTime start_date;
    private Long numberOfDaysRentals;
    private Integer cra;
}
