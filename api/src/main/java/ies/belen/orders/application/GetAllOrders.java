package ies.belen.orders.application;

import ies.belen.orders.domain.Order;
import ies.belen.orders.domain.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GetAllOrders {

    @Inject
    private OrderRepository orderRepository;

    public OrderDtoListResponse getAll() {
        return new OrderDtoListResponse(
                orderRepository
                    .getAll()
                    .stream()
                    .map(Order::toDto)
                    .toList()
        );
    }


}
