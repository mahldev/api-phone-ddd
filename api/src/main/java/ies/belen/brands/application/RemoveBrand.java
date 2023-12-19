package ies.belen.brands.application;

import ies.belen.brands.domain.BrandRepository;
import ies.belen.exceptions.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RemoveBrand {

    @Inject
    private BrandRepository brandRepository;

    public void remove(Long id) {
        brandRepository.findById(id).ifPresentOrElse(
                brand -> brandRepository.delete(brand),
                () -> {
                    throw new ResourceNotFoundException("Brand not found");
                });
    }

}
