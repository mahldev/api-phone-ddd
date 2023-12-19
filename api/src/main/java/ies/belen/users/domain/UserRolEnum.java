package ies.belen.users.domain;

import java.util.Arrays;

public enum UserRolEnum {
        ADMIN,
        USER;

        public static UserRolEnum fromString(String value) {
                return Arrays.stream(UserRolEnum.values())
                        .filter(userRolEnum -> value.equalsIgnoreCase(userRolEnum.toString()))
                        .findFirst()
                        .orElseThrow( () -> new IllegalArgumentException("unknown user rol"));
        }
}
