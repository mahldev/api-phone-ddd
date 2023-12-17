package ies.belen.brands.application;

import java.util.List;

public record BrandDtoListResponse(List<BrandDto> brands) {

    public void add(BrandDto brandDto) {
        this.brands.add(brandDto);
    }
}
