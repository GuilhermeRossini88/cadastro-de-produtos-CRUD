package br.com.rtech.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductsDTO(
        @NotBlank String name,
        @NotNull BigDecimal value
) {


}
