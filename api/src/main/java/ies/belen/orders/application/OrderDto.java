package ies.belen.orders.application;

import ies.belen.users.application.UserDto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(

        Long id,

        LocalDateTime date,

        UserDto user,

        List<OrderItemDto> phones
) {
}
