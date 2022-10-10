package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rentais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "car_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Cras cras;
    @JoinColumn(name = "costumer_id")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Costumers costumer;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private BigDecimal total;
    private Date created_at;
    private Date update_at;


}
