package ies.belen.orders.application;

import ies.belen.phones.application.PhoneDto;

public record OrderItemDto(

        PhoneDto phone,

        Integer quantity
) {
}
