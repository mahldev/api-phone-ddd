package ies.belen.phones.infrastructure;

import java.net.URI;

import ies.belen.phones.application.CreatePhone;
import ies.belen.phones.application.GetAllPhones;
import ies.belen.phones.application.PhoneDto;
import ies.belen.phones.application.RemovePhone;
import ies.belen.phones.application.UpdatePhone;
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

    @Inject
    public PhoneController(
            GetAllPhones getAllPhones,
            CreatePhone createPhone,
            RemovePhone removePhone,
            UpdatePhone updatePhone) {
        this.getAllPhones = getAllPhones;
        this.createPhone = createPhone;
        this.removePhone = removePhone;
        this.updatePhone = updatePhone;
    }

    @GET
    public Response getAll() {
        return Response.ok(getAllPhones.getAll()).build();
    }

    @POST
    public Response create(@Valid PhoneDto phoneDto) {
        PhoneDto phone = createPhone.create(phoneDto.name(), phoneDto.brandId());
        return Response
                .created(URI.create("/phones/" + phone.id()))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, PhoneDto phoneDto) {
        updatePhone.update(id, phoneDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        removePhone.remove(id);
        return Response.noContent().build();
    }
}
