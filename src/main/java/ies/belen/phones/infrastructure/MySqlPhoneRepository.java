package ies.belen.phones.infrastructure;

import java.util.List;
import java.util.Optional;

import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional
public class MySqlPhoneRepository implements PhoneRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Phone> getAll() {
        var query = "SELECT p FROM Phone p";
        return em.createQuery(query, Phone.class)
                .getResultList();
    }

    @Override
    public Optional<Phone> findById(Long id) {
        return Optional.ofNullable(em.find(Phone.class, id));
    }

    @Override
    public Optional<Phone> findByName(String name) {
        var query = "SELECT p FROM Phone p WHERE p.name = :name";
        return em.createQuery(query, Phone.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Phone save(Phone phone) {
        em.persist(phone);
        em.flush();
        return phone;
    }

    @Override
    public void update(Phone phone) {
        em.merge(phone);
    }

    @Override
    public void delete(Phone phone) {
        em.remove(phone);
    }

    @Override
    public void deleteById(Long id) {
        var query = "DELETE FROM Phone p WHERE p.id = :id";
        em.createQuery(query, Phone.class)
                .setParameter("id", id)
                .executeUpdate();
    }

}
