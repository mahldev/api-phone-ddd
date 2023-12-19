package ies.belen.orders.infrastructure;

import ies.belen.orders.domain.Order;
import ies.belen.orders.domain.OrderRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class MySqlOrderRepository implements OrderRepository {

    @Inject
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Order> getAll() {
        var query = "SELECT o FROM Order o";
        return em
                .createQuery(query, Order.class)
                .getResultStream()
                .toList();
    }

    @Override
    public Optional<Order> create(Order order) {
        em.persist(order);
        em.flush();
        return Optional.of(order);
    }
}
