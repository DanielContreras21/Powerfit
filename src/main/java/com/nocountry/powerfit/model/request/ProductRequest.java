package com.nocountry.powerfit.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {


    @NotEmpty(message = "Nombre no puede estar vacío")
    private String name;
    @NotEmpty(message = "Descripción no puede estar vacía")
    private String description;
    @NotNull(message = "Debe especificar el precio")
    @Min(value = 0, message = "El precio minimo es 0")
    private Double price;
    @NotNull(message = "Debe especificar el stock")
    @Min(value = 0, message = "EL stock debe ser número positivo")
    private Integer stock;
    @NotNull(message = "Debe especificar una categoría")
    private String category;

    

}