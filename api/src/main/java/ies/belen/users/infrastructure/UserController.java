package ies.belen.users.infrastructure;


import ies.belen.auth.ApiKeySecured;
import ies.belen.users.application.CreateUser;
import ies.belen.users.application.GetAllUsers;
import ies.belen.users.application.UserDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("users")
@ApiKeySecured
public class UserController {

    private final CreateUser createUser;
    private final GetAllUsers getAllUsers;

    @Inject
    public UserController (
            CreateUser createUser,
            GetAllUsers getAllUsers
    ) {
        this.createUser = createUser;
        this.getAllUsers = getAllUsers;
    }

    @GET
    public Response getAll() {
        List<UserDto> users = getAllUsers.getAll();
        return Response.ok(users).build();
    }

    @POST
    public Response create(UserDto userDtoPassword) {
        UserDto userDto = createUser.create(userDtoPassword);
       return Response
               .created(URI.create("/users/" + userDto.id()))
               .entity(userDto)
               .build();
    }
}
