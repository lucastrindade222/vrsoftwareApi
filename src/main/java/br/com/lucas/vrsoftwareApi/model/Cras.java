package br.com.lucas.vrsoftwareApi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cras {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String  description;
  private BigDecimal daily_rate;
  private boolean available;
  private String license_plate;
  @JoinColumn(name = "brand_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private Brand brand;
  @JoinColumn(name = "category_id")
  @ManyToOne(cascade = CascadeType.REFRESH)
  private Category category;
  private String color;
  private Date created_at;
  @JsonIgnore
  @OneToMany(mappedBy = "cras", fetch = FetchType.LAZY )
  private List<Rentais> rentais;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "cars_specifications",
          joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "specification_id", referencedColumnName = "id"))
  private Set<Specifications> specifications;




}
