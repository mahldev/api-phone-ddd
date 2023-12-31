package ies.belen.users.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll();

    Optional<User> save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByName(UserName name);

}
