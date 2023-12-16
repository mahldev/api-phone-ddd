package ies.belen.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(exceptionType(exception))
                .entity(new ExceptionDto(exception.getMessage()))
                .build();
    }

    private Status exceptionType(Exception exception) {

        if (exception instanceof IllegalArgumentException)
            return Status.BAD_REQUEST;

        if (exception instanceof ResourceConflictException)
            return Status.CONFLICT;

        if (exception instanceof ResourceNotFoundException)
            return Status.NOT_FOUND;

        return Status.INTERNAL_SERVER_ERROR;
    }

}