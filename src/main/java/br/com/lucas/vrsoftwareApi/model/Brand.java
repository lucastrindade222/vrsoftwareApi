package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import javax.persistence.GenerationType;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Brand  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date created_at;
    @JsonIgnore
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY )
    private List<Cras> crasList;

}
