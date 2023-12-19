package ies.belen.brands.application;

import ies.belen.brands.domain.Brand;
import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceConflictException;
import ies.belen.phones.domain.Phone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CreateBrand {

    @Inject
    private BrandRepository brandRepository;

    public BrandDto create(String name) {
        brandRepository.findByName(name).ifPresent(brand -> {
            throw new ResourceConflictException("Brand already exists");
        });

        return brandRepository.save(new Brand(name))
                .map(brand -> new BrandDto(
                        brand.getId(),
                        brand.getName(),
                        Phone.phoneSetToPhoneDtoList(brand.getPhones())))
                .orElseThrow();
    }
}
