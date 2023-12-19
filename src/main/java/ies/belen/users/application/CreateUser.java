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

    @Inject
    private UserRolRepository rolRepository;

    public UserDto create(UserDto userDtoPassword) {
       return userRepository
               .save(new User(
                       userDtoPassword.userName(),
                       userDtoPassword.password()
                       )
               )
               .map(savedUser -> {
                    savedUser.setRol(
                           rolRepository.findByRol(UserRolEnum.fromString(userDtoPassword.rol()))
                                   .orElseGet(() -> rolRepository.create(new UserRol(userDtoPassword.rol())))
                    );
                    return savedUser;
               })
               .map(User::toUserDto)
               .orElseThrow();
    }

}
