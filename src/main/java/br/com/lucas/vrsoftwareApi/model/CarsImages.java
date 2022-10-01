package br.com.lucas.vrsoftwareApi.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "cars_images")
public class CarsImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date created_at;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Cras car;
    @Lob
    @Column( columnDefinition="BLOB")
    private byte[] image;

}
