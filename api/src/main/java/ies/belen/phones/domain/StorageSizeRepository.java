package ies.belen.phones.domain;

import java.util.Optional;

public interface StorageSizeRepository {

    StorageSize create(StorageSize storageSize);

    Optional<StorageSize> findBySize(StorageSize.StorageSizeEnum sizeEnum);
}
