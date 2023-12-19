package ies.belen.users.infrastructure;

import ies.belen.users.domain.UserRol;
import ies.belen.users.domain.UserRolEnum;
import ies.belen.users.domain.UserRolRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;

@RequestScoped
@Transactional
public class MySqlUserRolRepository implements UserRolRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<UserRol> findByRol(UserRolEnum rol) {
        var query = "SELECT ur FROM UserRol ur WHERE ur.rolEnum = :rol";
        return em
                .createQuery(query, UserRol.class)
                .setParameter("rol", rol)
                .getResultStream()
                .findFirst();
    }

    @Override
    public UserRol create(UserRol userRol) {
        em.persist(userRol);
        em.flush();
        return userRol;
    }

}
