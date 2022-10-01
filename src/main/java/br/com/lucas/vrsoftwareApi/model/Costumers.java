package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Costumers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birth_date;
    @Email
    private String email;
    @Column(unique = true)
    private String driver_license;
    private String adress;
    private String phone_number;
    private Date created_at;
    private Date update_at;
    @OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY )
    List<Rentais> rentais;

}
