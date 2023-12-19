package ies.belen.utils;

import java.util.Set;
import java.util.HashSet;

import ies.belen.filters.ApiKeyFilter;
import ies.belen.orders.infrastructure.OrderController;
import ies.belen.users.infrastructure.UserController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import ies.belen.filters.CorsFilter;
import ies.belen.brands.infrastructure.BrandController;
import ies.belen.phones.infrastructure.PhoneController;
import ies.belen.exceptions.GlobalExceptionHandler;

@ApplicationPath("api")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CorsFilter.class);
        classes.add(PhoneController.class);
        classes.add(BrandController.class);
        classes.add(UserController.class);
        classes.add(OrderController.class);
        classes.add(GlobalExceptionHandler.class);
        classes.add(ApiKeyFilter.class);
        return classes;
    }

}
