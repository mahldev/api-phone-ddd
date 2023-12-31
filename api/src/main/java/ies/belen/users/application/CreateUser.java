package ies.belen.users.application;

import ies.belen.users.domain.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CreateUser {

  @Inject
  private UserRepository userRepository;

  public UserDto create(UserDto userDtoPassword) {
    return userRepository
        .save(new User(
            userDtoPassword.userName(),
            userDtoPassword.password()))
        .map(User::toUserDto)
        .orElseThrow();
  }

}
