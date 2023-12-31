package ies.belen.users.domain;

import ies.belen.users.application.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserName username;

    @Embedded
    private UserPassword password;

    public User(String userName, String password) {
        this.username = new UserName(userName);
        this.password = new UserPassword(password);
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername().getUsername(),
                user.getPassword().getPassword());
    }

}
