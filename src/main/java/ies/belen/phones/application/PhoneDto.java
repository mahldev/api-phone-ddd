package ies.belen.phones.application;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record PhoneDto(
                Long id,
                @NotBlank String name,
                @NotNull Double price,
                @NotNull Long brandId,
                @NotNull List<Integer> storagesSizes) {
}
