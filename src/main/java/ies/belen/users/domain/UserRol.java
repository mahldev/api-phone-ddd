package ies.belen.users.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "user_rols")
@Getter
@NoArgsConstructor
public class UserRol implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol")
    private UserRolEnum rolEnum;

    public UserRol (String rol) {
        this.rolEnum = UserRolEnum.fromString(rol);
    }

}
