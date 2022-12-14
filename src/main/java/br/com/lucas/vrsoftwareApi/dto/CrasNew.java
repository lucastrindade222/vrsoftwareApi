package br.com.lucas.vrsoftwareApi.dto;

import br.com.lucas.vrsoftwareApi.model.Brand;
import br.com.lucas.vrsoftwareApi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrasNew {

    private String name;
    private String  description;
    private BigDecimal daily_rate;
    private String license_plate;
    private Integer brand;
    private Integer category;
    private String color;
}
