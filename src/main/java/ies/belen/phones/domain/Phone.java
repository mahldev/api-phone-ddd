package ies.belen.phones.domain;

import static java.util.Objects.isNull;

import java.io.Serializable;

import ies.belen.brands.domain.Brand;
import ies.belen.phones.application.PhoneDto;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@Data
public class Phone implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany
    @JoinTable(
            name = "phone_storage_sizes",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_size_id")
    )
    private List<StorageSize> storageSizes;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
    private List<PhoneImage> images;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
    private List<PhoneColor> colors;

    public Phone(
            String name,
            Double price,
            Brand brand,
            List<String> images,
            List<String> colors
    ) {
        ensurePriceIsPositive(price);

        this.name = name;
        this.price = price;
        this.brand = brand;
        this.images = fromStringToPhoneImages(images);
        this.colors = fromListOfStringToListColor(colors);
    }

    public void setPrice(Double price) {
        ensurePriceIsPositive(price);

        this.price = price;
    }

    private void ensurePriceIsPositive(Double price) {
        if (isNull(price) || price < 0)
            throw new IllegalArgumentException("Invalid price");
    }

    public static List<PhoneDto> formListOfPhoneToPhoneDto(Set<Phone> phones) {
        return phones
                .stream()
                .map(Phone::toPhoneDto)
                .toList();
    }

    public static List<Integer> fromListOfStorageSizeToListOfInt(List<StorageSize> list) {
        return list
                .stream()
                .map(StorageSize::getSizeInGB)
                .mapToInt(StorageSize::fromStorageSizeEnumToInt)
                .boxed()
                .toList();
    }

    public static List<String> fromListOfPhoneImageToListOfString(List<PhoneImage> list) {
        return list
                .stream()
                .map(PhoneImage::getImageName)
                .toList();
    }

    private static List<String> fromListOfColorsToListOfString(List<PhoneColor> colors) {
       return colors
               .stream()
               .map(PhoneColor::getColorName)
               .toList();
    }

    private List<PhoneImage> fromStringToPhoneImages(List<String> stringList) {
        return stringList
                .stream()
                .map(imageString -> new PhoneImage(imageString, this))
                .toList();
    }

    private List<PhoneColor> fromListOfStringToListColor(List<String> stringList) {
        return stringList
                .stream()
                .map(color -> new PhoneColor(color, this))
                .toList();
    }

    public static PhoneDto toPhoneDto(Phone phone) {
        return new PhoneDto(
                phone.getId(),
                phone.getName(),
                phone.getPrice(),
                phone.getBrand().getId(),
                fromListOfStorageSizeToListOfInt(phone.getStorageSizes()),
                fromListOfPhoneImageToListOfString(phone.getImages()),
                fromListOfColorsToListOfString(phone.getColors())
        );
    }

}
