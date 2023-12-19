package ies.belen.phones.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "storages_sizes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="size_in_gb")
    private StorageSizeEnum sizeInGB;

    public StorageSize(int sizeInGB) {
        this.sizeInGB = fromIntToStorageSizeEnum(sizeInGB);
    }

    public StorageSize(StorageSizeEnum sizeEnum) {
        this.sizeInGB = sizeEnum;
    }

    public static StorageSizeEnum fromIntToStorageSizeEnum(int sizeInGB) {
         switch (sizeInGB) {
            case 64:
                return StorageSizeEnum.SIZE_64GB;
            case 128:
                return StorageSizeEnum.SIZE_128GB;
            case 256:
                return StorageSizeEnum.SIZE_256GB;
             case 1000:
                 return StorageSizeEnum.SIZE_1000GB;
            default:
                throw new IllegalArgumentException("Invalid sizeInGB value");
        }
    }

    public static int fromStorageSizeEnumToInt(StorageSizeEnum storageSizeEnum) {
        switch (storageSizeEnum) {
            case SIZE_64GB:
                return 64;
            case SIZE_128GB:
                return 128;
            case SIZE_256GB:
                return 256;
            case SIZE_1000GB:
                return 1000;
            default:
                throw new IllegalArgumentException("Invalid StorageSizeEnum value");
        }
    }

    public enum StorageSizeEnum {
        SIZE_64GB,
        SIZE_128GB,
        SIZE_256GB,
        SIZE_1000GB,
    }

}
