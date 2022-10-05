package br.com.lucas.vrsoftwareApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostumersNew {

     
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birth_date;
    @Email
    private String email;
    private String driver_license;
    private String adress;
    private String phone_number;



}
