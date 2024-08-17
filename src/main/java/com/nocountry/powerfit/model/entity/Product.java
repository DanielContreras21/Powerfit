package com.nocountry.powerfit.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Debe especificar un nombre")
    private String name;

    @NotEmpty(message = "Debe especificar una descripción")
    private String description;

    @NotNull(message = "Debe especificar el precio")
    @Min(value = 0, message = "El precio minimo es 0")
    private Double price;

    @NotNull(message = "Debe especificar el stock")
    @Min(value = 0, message = "El stock debe ser un numero positivo, minimo 0")
    private Integer stock;

    @NotNull(message = "Debe especificar una categoría")
    private String category;

//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Image> carrousel = new ArrayList<>();

}