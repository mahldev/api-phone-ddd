package ies.belen.login.infrastructure;

import ies.belen.exceptions.ExceptionDto;
import ies.belen.login.application.LoginUser;
import ies.belen.users.application.UserDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("login")
public class LoginController {

    private final LoginUser loginUser;

    @Inject
    public LoginController(final LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @POST
    public Response login(final UserDto userDto) {
        return loginUser.login(userDto)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(400).entity(new ExceptionDto("Invalid User")).build());
    }

}
