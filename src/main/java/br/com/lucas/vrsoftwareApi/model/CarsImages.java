package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CarsImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date created_at;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Cras car;
    @Lob
    private byte[] image;

}
