package ies.belen.filters;

import ies.belen.auth.ApiKeySecured;
import ies.belen.exceptions.ExceptionDto;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.Objects;

@Provider
@ApiKeySecured
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    private static final String API_KEY = "apikey";
    @Override
    public void filter(ContainerRequestContext requestContext) {
        String apiKey = requestContext.getHeaderString("Api-Key");
        if (!Objects.equals(apiKey, API_KEY))
                requestContext.abortWith(
                        Response
                                .status(Response.Status.UNAUTHORIZED)
                                .entity(new ExceptionDto(Response.Status.UNAUTHORIZED.getReasonPhrase()))
                                .build());
    }

}
