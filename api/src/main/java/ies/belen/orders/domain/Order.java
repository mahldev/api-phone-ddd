package ies.belen.orders.domain;

import ies.belen.orders.application.OrderDto;
import ies.belen.orders.application.OrderItemDto;
import ies.belen.users.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private OrderDate date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<OrderItem> orderItems;

  public Order(User user) {
    this.date = new OrderDate();
    this.user = user;
  }

  public static OrderDto toDto(Order order) {
    return new OrderDto(
        order.id,
        order.date.getDate(),
        User.toUserDto(order.user),
        toOrderItemDtoList(order.orderItems));
  }

  private static List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItems) {
    return orderItems.stream()
        .map(OrderItem::toDto)
        .toList();
  }

}
