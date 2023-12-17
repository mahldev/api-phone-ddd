package ies.belen.brands.application;

import java.util.List;
import java.util.Set;

import ies.belen.brands.domain.Brand;
import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceConflictException;
import ies.belen.phones.application.PhoneDto;
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
                        toListofPhonesDtop(brand.getPhones())))
                .orElseThrow();
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
