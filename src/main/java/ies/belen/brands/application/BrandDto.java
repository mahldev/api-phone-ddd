package ies.belen.brands.application;

import jakarta.validation.constraints.NotBlank;

public record BrandDto(
        Long id,
        @NotBlank String name) {
}
