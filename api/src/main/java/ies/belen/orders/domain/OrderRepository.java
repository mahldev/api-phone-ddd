package ies.belen.orders.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> getAll();

    Optional<Order> create(Order order);

}
