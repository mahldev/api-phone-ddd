package ies.belen.orders.application;

import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.orders.domain.Order;
import ies.belen.orders.domain.OrderItem;
import ies.belen.orders.domain.OrderRepository;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneRepository;
import ies.belen.users.domain.User;
import ies.belen.users.domain.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CreateOrder {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PhoneRepository phoneRepository;

    public OrderDto create(OrderDtoRequest orderDtoRequest) {
        User user = userRepository.findById(orderDtoRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        List<OrderItem> orderItems = orderDtoRequest
                .items()
                .stream()
                .map(this::orderItemDtoRequestToOrderItem)
                .toList();

        return orderRepository
                .create(new Order(user))
                .map(savedOrder -> {
                    orderItems.forEach(orderItem -> orderItem.setOrder(savedOrder));
                    savedOrder.setOrderItems(orderItems);
                    return savedOrder;
                })
                .map(Order::toDto)
                .orElseThrow();
    }

    private OrderItem orderItemDtoRequestToOrderItem(OrderItemDtoRequest orderItemDtoRequest) {
        Phone phone = phoneRepository.findById(orderItemDtoRequest.phoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Phone with id " + orderItemDtoRequest.phoneId() + " not found" ));

        return new OrderItem(phone, orderItemDtoRequest.quantity());
    }

}
