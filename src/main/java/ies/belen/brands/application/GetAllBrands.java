package ies.belen.brands.application;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.phones.domain.Phone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GetAllBrands {

    @Inject
    private BrandRepository brandRepository;

    public BrandDtoListResponse getAll() {
        return new BrandDtoListResponse(brandRepository.getAll()
                .stream()
                .map(brand -> new BrandDto(
                        brand.getId(),
                        brand.getName(),
                        Phone.toListOfPhonesDtop(brand.getPhones())))
                .toList());
    }

}
