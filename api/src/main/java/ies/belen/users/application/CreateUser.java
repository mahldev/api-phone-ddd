package ies.belen.users.application;

import ies.belen.exceptions.ResourceConflictException;
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
        final User user = UserDto.toUser(userDtoPassword);

        userRepository.findByName(user.getUsername()).ifPresent(unused -> {
            throw new ResourceConflictException("Invalid username");
        });

        return userRepository.save(user)
                .map(User::toUserDto)
                .orElseThrow();
    }

}
