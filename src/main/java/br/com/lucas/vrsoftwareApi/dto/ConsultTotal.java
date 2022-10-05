package br.com.lucas.vrsoftwareApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultTotal {
    public BigDecimal totalValue;
}
