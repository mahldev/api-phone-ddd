package ies.belen.phones.domain;

import static java.util.Objects.isNull;

import java.io.Serializable;

import ies.belen.brands.domain.Brand;
import ies.belen.phones.application.PhoneDto;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "phones_storages",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_size_id")
    )
    private List<StorageSize> storagesSizes;

    public Phone(String name, Double price, Brand brand, List<Integer> storages) {
        ensurePriceIsPositive(price);

        this.name = name;
        this.price = price;
        this.brand = brand;
        this.storagesSizes = fromIntegerToStorageSizes(storages);
    }

    public void setPrice(Double price) {
        ensurePriceIsPositive(price);

        this.price = price;
    }

    public void setStoragesSizes(List<Integer> storages) {
        this.storagesSizes = fromIntegerToStorageSizes(storages);
    }

    private void ensurePriceIsPositive(Double price) {
        if (isNull(price) || price < 0)
            throw new IllegalArgumentException("Invalid price");
    }

    private List<StorageSize> fromIntegerToStorageSizes(List<Integer> list) {
        return list.stream()
                .map(StorageSize::fromIntToStorageSize)
                .toList();
    }

    public static List<PhoneDto> formListOfPhoneToPhoneDto(Set<Phone> phones) {
        return phones.stream()
                .map(Phone::toPhoneDto)
                .toList();
    }

    public static List<Integer> fromListOfStorageSizeToListOfInt(List<StorageSize> list) {
        return list.stream()
                .mapToInt(StorageSize::getSizeInGB)
                .boxed()
                .toList();
    }

    public static PhoneDto toPhoneDto(Phone phone) {
        return new PhoneDto(
                phone.getId(),
                phone.getName(),
                phone.getPrice(),
                phone.getBrand().getId(),
                fromListOfStorageSizeToListOfInt(phone.getStoragesSizes()));
    }

    public static Phone createPhoneFromPhoneDto(PhoneDto phoneDto, Brand brand) {
        return new Phone(
                phoneDto.name(),
                phoneDto.price(),
                brand,
                phoneDto.storagesSizes());
    }
}
