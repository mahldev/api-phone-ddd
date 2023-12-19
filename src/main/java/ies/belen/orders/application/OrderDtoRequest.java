package ies.belen.orders.application;

import java.util.List;

public record OrderDtoRequest(

        Long userId,

        List<OrderItemDtoRequest> items

) {
}
