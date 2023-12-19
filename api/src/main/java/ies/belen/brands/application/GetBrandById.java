package ies.belen.brands.application;

import ies.belen.brands.domain.Brand;
import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GetBrandById {

    @Inject
    private BrandRepository brandRepository;

    public BrandDto get(Long id) {
        return brandRepository.findById(id)
                .map(Brand::toBrandDto)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }

}
