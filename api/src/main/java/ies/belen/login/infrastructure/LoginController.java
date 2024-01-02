package ies.belen.login.infrastructure;

import ies.belen.auth.ApiKeySecured;
import ies.belen.exceptions.ExceptionDto;
import ies.belen.login.application.LoginUser;
import ies.belen.users.application.UserDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
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
        boolean access = loginUser.login(userDto);
        return access
                ? Response.ok().build()
                : Response.status(400).entity(new ExceptionDto("Invalid User")).build();
    }

    @GET
    public void test() {
        System.out.println("Ha entrado un get ");
    }

}
