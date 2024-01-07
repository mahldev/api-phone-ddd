package ies.belen.orders.infrastructure;

import ies.belen.auth.ApiKeySecured;
import ies.belen.orders.application.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("orders")
@ApiKeySecured
public class OrderController {

    private final GetAllOrders getAllOrders;

    private final CreateOrder createOrder;

    @Inject
    public OrderController(
            GetAllOrders getAllOrders,
            CreateOrder createOrder) {
        this.getAllOrders = getAllOrders;
        this.createOrder = createOrder;
    }

    @GET
    public Response getAll() {
        return Response.ok(getAllOrders.getAll()).build();
    }

    @POST
    public Response create(OrderDtoRequest orderDtoRequest) {
        OrderDto order = createOrder.create(orderDtoRequest);
        return Response
                .created(URI.create("/order/" + order.id()))
                .entity(order)
                .build();
    }
}
