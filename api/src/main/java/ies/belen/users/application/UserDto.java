package ies.belen.users.application;

import ies.belen.users.domain.User;

public record UserDto(

        Long id,

        String userName,

        String password

) {

    public static User toUser(final UserDto userDto) {
        return new User(
                userDto.userName(),
                userDto.password());
    }
}
