package ies.belen.phones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Embeddable
@Getter
@NoArgsConstructor
public class PhonePrice {

    @Column(name = "price")
    private Double price;

    public PhonePrice(Double price) {
        ensurePriceIsPositive(price);

        this.price = price;
    }

    public void setPrice(Double price) {
        ensurePriceIsPositive(price);

        this.price = price;
    }

    private void ensurePriceIsPositive(Double price) {
        if (isNull(price) || price < 0)
            throw new IllegalArgumentException("Invalid price");
    }
}
