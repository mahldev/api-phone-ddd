package ies.belen.users.domain;

import java.util.Optional;

public interface UserRolRepository {

    Optional<UserRol> findByRol(UserRolEnum rol);

    UserRol create(UserRol userRol);
}
