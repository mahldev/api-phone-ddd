package ies.belen.phones.application;

import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.phones.domain.PhoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class RemovePhone {

    @Inject
    private PhoneRepository phoneRepository;

    public void remove(Long id) {
        phoneRepository.findById(id).ifPresentOrElse(
                (phone) -> {
                    phoneRepository.delete(phone);
                    phone.getBrand().getPhones().remove(phone);
                },
                () -> {
                    throw new ResourceNotFoundException("Phone not found");
                });
    }
}
