package ies.belen.orders.application;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(

        Long id,

        LocalDateTime date,

        Long userId,

        List<OrderItemDto> items

) {
}
