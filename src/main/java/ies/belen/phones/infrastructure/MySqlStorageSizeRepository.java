package ies.belen.phones.infrastructure;

import ies.belen.phones.domain.StorageSize;
import ies.belen.phones.domain.StorageSizeRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@RequestScoped
@Transactional
public class MySqlStorageSizeRepository implements StorageSizeRepository {

    @Inject
    private EntityManager em;

    @Override
    public StorageSize create(StorageSize storageSize) {
        em.persist(storageSize);
        em.flush();
        return storageSize;
    }

    @Override
    public Optional<StorageSize> findBySize(StorageSize.StorageSizeEnum size) {
        var query = "SELECT s from StorageSize s WHERE s.sizeInGB = :size";
        return em.createQuery(query,StorageSize.class)
                .setParameter("size", size)
                .getResultStream()
                .findFirst();
    }
}
