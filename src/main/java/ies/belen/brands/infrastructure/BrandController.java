package ies.belen.brands.infrastructure;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/brands")
public class BrandController {

    @GET
    public String getBrands() {
        return "Hello World! whthout build";
    }

    @GET
    @Path("/compose")
    public String getBrandById() {
        return "Hello World! from docker-compose";
    }
}
