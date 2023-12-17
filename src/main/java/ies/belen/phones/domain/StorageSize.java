package ies.belen.phones.domain;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    private int sizeInGB;

    public StorageSize(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public static StorageSize fromIntToStorageSize(int sizeInGB) {
        return new StorageSize(sizeInGB);
    }

}
