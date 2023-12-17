package ies.belen.brands.application;

import java.util.List;
import java.util.Set;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.phones.application.PhoneDto;
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
                .map(brand -> new BrandDto(brand.getId(), brand.getName(), toListofPhonesDtop(brand.getPhones())))
                .toList());
    }

    private List<PhoneDto> toListofPhonesDtop(Set<Phone> phones) {
        return phones.stream()
                .map(phone -> new PhoneDto(
                        phone.getId(),
                        phone.getName(),
                        phone.getBrand().getId()))
                .toList();
    }
}
