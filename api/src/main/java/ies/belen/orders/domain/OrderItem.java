package ies.belen.orders.domain;

import ies.belen.orders.application.OrderItemDto;
import ies.belen.phones.domain.Color;
import ies.belen.phones.domain.Phone;
import ies.belen.phones.domain.StorageSize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "storage_size")
    private StorageSize storage;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Embedded
    private OrderItemQuantity quantity;

    public OrderItem(Phone phone, Integer quantity, Color color, StorageSize storageSize) {
        this.phone = phone;
        this.quantity = new OrderItemQuantity(quantity);
        this.color = color;
        this.storage = storageSize;
    }

    public static OrderItemDto toDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.phone.getId(),
                StorageSize.fromStorageSizeEnumToInt(orderItem.getStorage().getSizeInGB()),
                Color.fromColorEnumToString(orderItem.getColor().getColor()),
                orderItem.getQuantity().getQuantity());
    }
}
