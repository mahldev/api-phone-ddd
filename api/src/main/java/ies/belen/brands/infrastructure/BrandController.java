package ies.belen.brands.infrastructure;

import java.net.URI;

import ies.belen.auth.ApiKeySecured;
import ies.belen.brands.application.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("brands")
@ApiKeySecured
public class BrandController {

    private final CreateBrand createBrand;
    private final GetAllBrands getAllBrands;
    private final UpdateBrand updateBrand;
    private final RemoveBrand removeBrand;
    private final GetBrandById getBrandById;

    @Inject
    public BrandController(
            final CreateBrand createBrand,
            final GetAllBrands getAllBrands,
            final UpdateBrand updateBrand,
            final RemoveBrand removeBrand,
            final GetBrandById getBrandById) {
        this.createBrand = createBrand;
        this.getAllBrands = getAllBrands;
        this.updateBrand = updateBrand;
        this.removeBrand = removeBrand;
        this.getBrandById = getBrandById;
    }

    @GET
    public Response getAll() {
        final BrandDtoListResponse brands = getAllBrands.getAll();
        return Response.ok(brands).build();
    }

    @GET
    @Path("{id}")
    public Response getBrand(@PathParam("id") final Long id) {
        final BrandDto brandDto = getBrandById.get(id);
        return Response.ok(brandDto).build();
    }

    @POST
    public Response save(@Valid final BrandDto brandDto) {
        final BrandDto brand = createBrand.create(brandDto.name());
        return Response
                .created(URI.create("/brands/" + brand.id()))
                .entity(brand)
                .build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") final Long id, @Valid final BrandDto brandDto) {
        updateBrand.update(id, brandDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final Long id) {
        removeBrand.remove(id);
        return Response.noContent().build();
    }

}
