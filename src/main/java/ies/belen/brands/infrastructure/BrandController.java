package ies.belen.brands.infrastructure;

import java.net.URI;

import ies.belen.brands.application.BrandDto;
import ies.belen.brands.application.CreateBrand;
import ies.belen.brands.application.GetAllBrands;
import ies.belen.brands.application.RemoveBrand;
import ies.belen.brands.application.UpdateBrand;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/brands")
public class BrandController {

    private final CreateBrand createBrand;
    private final GetAllBrands getAllBrands;
    private final UpdateBrand updateBrand;
    private final RemoveBrand removeBrand;

    @Inject
    public BrandController(
            CreateBrand createBrand,
            GetAllBrands getAllBrands,
            UpdateBrand updateBrand,
            RemoveBrand removeBrand) {
        this.createBrand = createBrand;
        this.getAllBrands = getAllBrands;
        this.updateBrand = updateBrand;
        this.removeBrand = removeBrand;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var brands = getAllBrands.getAll();
        return Response.ok(brands).build();
    }

    @POST
    public Response save(@Valid BrandDto brandDto) {
        var brand = createBrand.create(brandDto.name());
        return Response
                .created(URI.create("/brands/" + brand.id()))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid BrandDto brandDto) {
        updateBrand.update(id, brandDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        removeBrand.remove(id);
        return Response.noContent().build();
    }

}