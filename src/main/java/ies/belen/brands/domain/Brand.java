package ies.belen.brands.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ies.belen.phones.domain.Phone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brand implements Serializable {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Phone> phones;

    public Brand(String name) {
        this.name = name;
        this.phones = new HashSet<>();
    }

}
