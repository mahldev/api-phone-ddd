package ies.belen.brands.domain;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {

    List<Brand> getAll();

    Optional<Brand> save(Brand brand);

    Optional<Brand> findByName(String name);

    Optional<Brand> findById(Long id);

    void update(Brand brand);

    void delete(Brand brand);

    void deleteById(Long id);

}
