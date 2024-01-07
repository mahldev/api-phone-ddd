package ies.belen.orders.application;

public record OrderItemDto(

        Long phoneId,

        Integer storage,

        String color,

        Integer quantity

) {
}
