package ies.belen.phones.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "phone_colors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneColor implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30 ,name = "color_name")
    private String colorName;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Phone phone;

    public PhoneColor(String colorName, Phone phone) {
        this.colorName = colorName;
        this.phone = phone;
    }

}
