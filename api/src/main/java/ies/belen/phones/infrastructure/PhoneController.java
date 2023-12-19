package ies.belen.phones.infrastructure;

import java.net.URI;

import ies.belen.auth.ApiKeySecured;
import ies.belen.phones.application.CreatePhone;
import ies.belen.phones.application.GetAllPhones;
import ies.belen.phones.application.PhoneDto;
import ies.belen.phones.application.RemovePhone;
import ies.belen.phones.application.UpdatePhone;
import ies.belen.phones.application.GetPhoneById;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("phones")
public class PhoneController {

    private final GetAllPhones getAllPhones;
    private final CreatePhone createPhone;
    private final RemovePhone removePhone;
    private final UpdatePhone updatePhone;
    private final GetPhoneById getPhone;

    @Inject
    public PhoneController(
            GetAllPhones getAllPhones,
            CreatePhone createPhone,
            RemovePhone removePhone,
            UpdatePhone updatePhone,
            GetPhoneById getPhone
    ) {
        this.getAllPhones = getAllPhones;
        this.createPhone = createPhone;
        this.removePhone = removePhone;
        this.updatePhone = updatePhone;
        this.getPhone = getPhone;
    }

    @GET
    public Response getAll() {
        return Response.ok(getAllPhones.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        return Response.ok(getPhone.get(id)).build();
    }

    @POST
    @ApiKeySecured
    public Response create(@Valid PhoneDto phoneDto) {
        PhoneDto phone = createPhone.create(phoneDto);
        return Response
                .created(URI.create("/phones/" + phone.id()))
                .entity(phone)
                .build();
    }

    @PUT
    @Path("{id}")
    @ApiKeySecured
    public Response update(@PathParam("id") Long id, PhoneDto phoneDto) {
        updatePhone.update(id, phoneDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @ApiKeySecured
    public Response delete(@PathParam("id") Long id) {
        removePhone.remove(id);
        return Response.noContent().build();
    }
}
