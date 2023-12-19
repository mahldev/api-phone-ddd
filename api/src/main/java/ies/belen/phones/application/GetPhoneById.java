package ies.belen.phones.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import ies.belen.phones.domain.PhoneRepository;
import ies.belen.phones.domain.Phone;
import ies.belen.exceptions.ResourceNotFoundException;

@ApplicationScoped
@Transactional
public class GetPhoneById {

    @Inject
    private PhoneRepository phoneRepository;

    public PhoneDto get(Long id) {
        return phoneRepository.findById(id)
            .map(Phone::toPhoneDto)
            .orElseThrow(() -> new ResourceNotFoundException("Phone not found"));
    }
}
