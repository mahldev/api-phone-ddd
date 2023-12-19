package ies.belen.phones.application;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Objects;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
@Transactional
public class UpdatePhone {

    @Inject
    private PhoneRepository phoneRepository;

    @Inject
    private BrandRepository brandRepository;

    public void update(Long id, PhoneDto phoneDto) {

        if (isNull(phoneDto))
            throw new BadRequestException("No phone information provided for the update.");

        if (nonNull(phoneDto.name()))
            phoneRepository.findByName(phoneDto.name()).ifPresent(phone -> {
                if (!Objects.equals(phone.getName(), phoneDto.name()))
                    throw new IllegalArgumentException("Invalid name");
            });

        phoneRepository.findById(id).ifPresentOrElse(
                (phone) -> updateGivenInfo(phone, phoneDto),
                () -> {
                    throw new ResourceNotFoundException("Phone not found");
                });

    }

    private void updateGivenInfo(Phone phone, PhoneDto phoneDto) {

        if (nonNull(phoneDto.brandId())) {
            brandRepository.findById(phoneDto.brandId()).ifPresentOrElse(
                    (brand) -> {
                        updateOldBrand(phone);
                        phone.setBrand(brand);
                        brand.getPhones().add(phone);
                    },
                    () -> {
                        throw new ResourceNotFoundException("Brand not found");
                    });
        }

        if (nonNull(phoneDto.name()))
            phone.setName(phoneDto.name());

        if (nonNull(phoneDto.price()))
            phone.setPrice(phoneDto.price());


        phoneRepository.update(phone);
    }

    private void updateOldBrand(Phone phone) {
        brandRepository.findById(phone.getId()).ifPresent(
                (brand) -> brand.getPhones().remove(phone));

    }

}
