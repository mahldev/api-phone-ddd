package ies.belen.brands.application;

import java.util.List;

import ies.belen.phones.application.PhoneDto;
import jakarta.validation.constraints.NotBlank;

public record BrandDto(
                Long id,
                @NotBlank String name,
                List<PhoneDto> phones) {
}
