package ies.belen.users.infrastructure;

import ies.belen.users.domain.User;
import ies.belen.users.domain.UserName;
import ies.belen.users.domain.UserRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class MySqlUserRepository implements UserRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        final var query = "SELECT u FROM User u";
        return em
                .createQuery(query, User.class)
                .getResultStream()
                .toList();
    }

    @Override
    public Optional<User> save(final User user) {
        em.persist(user);
        em.flush();
        return Optional.of(user);
    }

    @Override
    public Optional<User> findById(final Long id) {
        final var query = "SELECT u FROM User u WHERE u.id = :id";
        return em
                .createQuery(query, User.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<User> findByName(UserName name) {
        final var query = "SELECT u FROM User u WHERE u.username = :name";
        return em
                .createQuery(query, User.class)
                .setParameter("name", name)
                .getResultStream()
                .findFirst();
    }

}
