package ies.belen.orders.domain;


import ies.belen.orders.application.OrderItemDto;
import ies.belen.phones.domain.Phone;
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
    @JoinColumn(name = "order_id")
    private Order order;

    @Embedded
    private OrderItemQuantity quantity;

   public OrderItem(Phone phone, Integer quantity) {
       this.phone = phone;
       this.quantity = new OrderItemQuantity(quantity);
   }

    public static OrderItemDto toDto (OrderItem orderItem) {
        return new OrderItemDto(
                Phone.toPhoneDto(orderItem.phone),
                orderItem.quantity.getQuantity()
        );
    }
}
