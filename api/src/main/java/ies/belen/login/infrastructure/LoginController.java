package ies.belen.login.infrastructure;

import ies.belen.auth.ApiKeySecured;
import ies.belen.login.application.LoginUser;
import ies.belen.users.application.UserDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("login")
@ApiKeySecured
public class LoginController {

    private final LoginUser loginUser;

    @Inject
    public LoginController(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @POST
    public Response login(UserDto userDto) {
        loginUser.login(userDto);
        return Response.ok().build();
    }

}
