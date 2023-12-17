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

    public PhoneDto create(String name, Long idBrand) {
        phoneRepository.findByName(name).ifPresent((unused) -> {
            throw new ResourceConflictException("Phone already exists");
        });

        return brandRepository.findById(idBrand)
                .map(brand -> {
                    Phone savedPhone = phoneRepository.save(new Phone(name, brand));
                    brand.getPhones().add(savedPhone);
                    return savedPhone;
                })
                .map(phone -> new PhoneDto(phone.getId(), phone.getName(), phone.getBrand().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }

}
