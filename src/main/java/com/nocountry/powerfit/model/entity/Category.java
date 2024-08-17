package com.nocountry.powerfit.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La categoría no puede ser nula")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Por favor ingrese un nombre válido a la categoría")
    private String name;

}
