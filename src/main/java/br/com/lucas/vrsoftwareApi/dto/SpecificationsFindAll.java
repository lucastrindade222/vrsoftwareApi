package br.com.lucas.vrsoftwareApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationsFindAll {
    private Integer id;
    private String name;
    private String description;
    private Date created_at;
}
