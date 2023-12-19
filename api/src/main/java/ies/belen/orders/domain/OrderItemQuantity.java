package ies.belen.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderItemQuantity {

    @Column(name = "quantity")
    private int quantity;

    public OrderItemQuantity(int quantity) {
        insureQuantityIsValid(quantity);

        this.quantity = quantity;
    }

    private void insureQuantityIsValid(int quantity) {
        if (quantity < 0 || quantity > 50)
            throw new IllegalArgumentException("Invalid quantity");
    }

}
