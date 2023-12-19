package ies.belen.phones.domain;

import static java.util.Objects.isNull;

import java.io.Serializable;

import ies.belen.brands.domain.Brand;
import ies.belen.phones.application.PhoneDto;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
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

    @Embedded
    private PhonePrice price;

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
        this.name = name;
        this.price = new PhonePrice(price);
        this.brand = brand;
        this.images = stringListToPhoneImageList(images);
        this.colors = stringListToPhoneColorList(colors);
    }

    public void setPrice(Double price) {
        this.price = new PhonePrice(price);
    }

    public static List<PhoneDto> phoneSetToPhoneDtoList(Set<Phone> phones) {
        return phones
                .stream()
                .map(Phone::toPhoneDto)
                .toList();
    }

    public static List<Integer> storageSizeToIntegerList(List<StorageSize> list) {
        return list
                .stream()
                .map(StorageSize::getSizeInGB)
                .mapToInt(StorageSize::fromStorageSizeEnumToInt)
                .boxed()
                .toList();
    }

    public static List<String> phoneImagesListToStringList(List<PhoneImage> list) {
        return list
                .stream()
                .map(PhoneImage::getImageName)
                .toList();
    }

    private static List<String> phoneColorListToStringList(List<PhoneColor> colors) {
       return colors
               .stream()
               .map(PhoneColor::getColorName)
               .toList();
    }

    private List<PhoneImage> stringListToPhoneImageList(List<String> stringList) {
        return stringList
                .stream()
                .map(imageString -> new PhoneImage(imageString, this))
                .toList();
    }

    private List<PhoneColor> stringListToPhoneColorList(List<String> stringList) {
        return stringList
                .stream()
                .map(color -> new PhoneColor(color, this))
                .toList();
    }

    public static PhoneDto toPhoneDto(Phone phone) {
        return new PhoneDto(
                phone.getId(),
                phone.getName(),
                phone.getPrice().getPrice(),
                phone.getBrand().getId(),
                storageSizeToIntegerList(phone.getStorageSizes()),
                phoneImagesListToStringList(phone.getImages()),
                phoneColorListToStringList(phone.getColors())
        );
    }

}
