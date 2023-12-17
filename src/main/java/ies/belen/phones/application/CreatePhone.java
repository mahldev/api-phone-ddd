package ies.belen.phones.application;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceConflictException;
import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CreatePhone {

    @Inject
    private PhoneRepository phoneRepository;

    @Inject
    private BrandRepository brandRepository;

    public PhoneDto create(PhoneDto phoneDto) {
        phoneRepository.findByName(phoneDto.name()).ifPresent((unused) -> {
            throw new ResourceConflictException("Phone already exists");
        });

        return brandRepository.findById(phoneDto.brandId())
                .map(brand -> {
                    Phone savedPhone = phoneRepository.save(Phone.createPhoneFromPhoneDto(phoneDto, brand));
                    brand.getPhones().add(savedPhone);
                    return savedPhone;
                })
                .map(Phone::toPhoneDto)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }

}
