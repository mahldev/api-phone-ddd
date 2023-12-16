package ies.belen.utils;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.brands.infrastructure.MySqlBrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class RepositoryProducer {

    @Produces
    @ApplicationScoped
    public BrandRepository produceBrandRepository() {
        return new MySqlBrandRepository();
    }
}