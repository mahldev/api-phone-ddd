package ies.belen.phones.application;

import ies.belen.phones.domain.PhoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GetAllPhones {

    @Inject
    private PhoneRepository phoneRepository;

    public PhoneDtoListResponse getAll() {
        return new PhoneDtoListResponse(phoneRepository.getAll()
                .stream()
                .map(phone -> new PhoneDto(phone.getId(), phone.getName(), phone.getBrand().getId()))
                .toList());
    }
}
