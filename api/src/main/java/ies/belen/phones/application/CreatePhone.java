package ies.belen.phones.application;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceConflictException;
import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.phones.domain.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CreatePhone {

    @Inject
    private PhoneRepository phoneRepository;

    @Inject
    private BrandRepository brandRepository;

    @Inject
    private StorageSizeRepository storageSizeRepository;

    @Inject
    private ColorRepository colorRepository;

    public PhoneDto create(PhoneDto phoneDto) {
        phoneRepository.findByName(phoneDto.name()).ifPresent((phone) -> {
            throw new ResourceConflictException("Phone already exists");
        });

        return brandRepository.findById(phoneDto.brandId())
                .map(brand -> {
                    Phone savedPhone = phoneRepository.save(new Phone(
                            phoneDto.name(),
                            phoneDto.price(),
                            brand,
                            phoneDto.images()));

                    savedPhone.setColors(phoneDto.colors()
                            .stream()
                            .map(color -> PhoneColorDto.toPhoneColor(color.commercialName(), savedPhone,
                                    findOrCreateColor(color.color().name())))
                            .toList());

                    savedPhone.getColors().stream();

                    savedPhone.setStorageSizes(fromIntegerListToSizeEnumList(phoneDto.storagesSizes()));
                    brand.getPhones().add(savedPhone);

                    return savedPhone;
                })
                .map(Phone::toPhoneDto)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
    }

    public List<StorageSize> fromIntegerListToSizeEnumList(List<Integer> integerList) {
        return integerList
                .stream()
                .map(StorageSize::fromIntToStorageSizeEnum)
                .map(sizeEnum -> storageSizeRepository.findBySize(sizeEnum)
                        .orElseGet(() -> storageSizeRepository.create(new StorageSize(sizeEnum))))
                .toList();
    }

    public Color findOrCreateColor(String name) {
        return colorRepository.findByName(Color.fromStringToColorEnum(name))
                .orElseGet(() -> colorRepository.create(new Color(name)));
    }
}
