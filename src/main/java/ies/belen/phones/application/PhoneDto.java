package ies.belen.phones.application;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record PhoneDto(
                Long id,
                @NotBlank String name,
                @NotNull Long brandId) {
}
