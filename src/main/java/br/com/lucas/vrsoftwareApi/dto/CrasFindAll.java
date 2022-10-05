package br.com.lucas.vrsoftwareApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrasFindAll {
    private Integer id;
    private String name;
    private String  description;
    private BigDecimal daily_rate;
    private boolean available;
    private String license_plate;
    private String color;
    private Date created_at;
}
