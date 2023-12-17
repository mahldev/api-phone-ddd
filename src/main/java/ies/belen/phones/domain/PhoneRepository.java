package ies.belen.phones.domain;

import java.util.List;
import java.util.Optional;

public interface PhoneRepository {

    List<Phone> getAll();

    Optional<Phone> findById(Long id);

    Optional<Phone> findByName(String name);

    Phone save(Phone phone);

    void update(Phone phone);

    void delete(Phone phone);

    void deleteById(Long id);
}
