package ies.belen.brands.application;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UpdateBrand {

    @Inject
    private BrandRepository brandRepository;

    public void update(Long id, BrandDto brandDto) {
        brandRepository.findById(id).ifPresentOrElse(
                (brand) -> {
                    brand.setName(brandDto.name());
                    brandRepository.update(brand);
                },
                () -> {
                    throw new ResourceNotFoundException("Brand not found");
                });
    }

}
