package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String discretion;
    private Date created_at;
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY )
    private List<Cras> crasList;

}
