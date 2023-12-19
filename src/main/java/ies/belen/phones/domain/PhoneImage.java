package ies.belen.phones.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "phone_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneImage implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, name = "image_name")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Phone phone;

    public PhoneImage(String imageName, Phone phone) {
        this.imageName = imageName;
        this.phone = phone;
    }
}
