package ies.belen.orders.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class OrderDate {

    @Column(name = "date")
    private LocalDateTime date;

    public OrderDate() {
        this.date = LocalDateTime.now();
    }

}
