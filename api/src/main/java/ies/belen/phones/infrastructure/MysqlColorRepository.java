package ies.belen.phones.infrastructure;

import java.util.Optional;

import ies.belen.phones.domain.Color;
import ies.belen.phones.domain.Color.ColorEnum;
import ies.belen.phones.domain.ColorRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
@Transactional
public class MysqlColorRepository implements ColorRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public Color create(final Color color) {
        em.persist(color);
        em.flush();
        return color;
    }

    @Override
    public Optional<Color> findByName(final ColorEnum color) {
        final var query = "SELECT c from Color c WHERE c.color = :color";
        return em.createQuery(query, Color.class)
                .setParameter("color", color)
                .getResultStream()
                .findFirst();
    }

}
