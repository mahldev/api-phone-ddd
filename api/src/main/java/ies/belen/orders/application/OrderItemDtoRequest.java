package ies.belen.orders.application;

import ies.belen.phones.application.ColorDto;

public record OrderItemDtoRequest(

        Long phoneId,

        ColorDto color,

        Integer storageSize,

        Integer quantity

) {
}
