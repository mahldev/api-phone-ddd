package ies.belen.phones.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "phone_colors")
@NoArgsConstructor
@Data
public class PhoneColor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "color_commercial_name")
    private String commercialName;

    @Column(length = 30, name = "color_name")
    private String colorName;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Phone phone;

    public PhoneColor(String commercialName, String colorName, Phone phone) {
        this.commercialName = commercialName;
        this.colorName = colorName;
        this.phone = phone;
    }

}
