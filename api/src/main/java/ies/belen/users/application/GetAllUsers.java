package ies.belen.users.application;

import ies.belen.users.domain.User;
import ies.belen.users.domain.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class GetAllUsers {

    @Inject
    private UserRepository userRepository;

    public List<UserDto> getAll() {
        return userRepository
                .getAll()
                .stream()
                .map(User::toUserDto)
                .toList();
    }

}


