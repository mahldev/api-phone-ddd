package ies.belen.orders.application;

import ies.belen.exceptions.ResourceNotFoundException;
import ies.belen.orders.domain.Order;
import ies.belen.orders.domain.OrderItem;
import ies.belen.orders.domain.OrderRepository;
import ies.belen.phones.domain.Color;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.PhoneRepository;
import ies.belen.phones.domain.StorageSize;
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

    public OrderDto create(final OrderDtoRequest orderDtoRequest) {
        final User user = userRepository.findById(orderDtoRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        final List<OrderItem> orderItems = orderDtoRequest
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

    private OrderItem orderItemDtoRequestToOrderItem(final OrderItemDtoRequest orderItem) {
        final Phone phone = phoneRepository.findById(orderItem.phoneId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Phone with id " + orderItem.phoneId() + " not found"));

        final var storageSize = phone.getStorageSizes()
                .stream()
                .filter(size -> size.equals(new StorageSize(orderItem.storageSize())))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Storage size not found for the given order item"));

        final var color = phone.getColors()
                .stream()
                .map(c -> c.getColor())
                .filter(c -> c.equals(new Color(orderItem.color().name())))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Color not found for the given order item"));

        return new OrderItem(
                phone,
                orderItem.quantity(),
                color,
                storageSize);
    }

}
