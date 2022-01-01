package ro.unibuc.fmi.dietapp.backend.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder)
    {
        return routeLocatorBuilder.routes()
                .route("diets-microservice", rt-> rt.path("/diets/**", "/foods/**", "/ingredients/**")
                        .uri("lb://diets-microservice"))
                .route("payments-microservice", rt-> rt.path("/payments/**")
                        .uri("lb://payments-microservice"))
                .route("users-microservice", rt-> rt.path("/accounts/**","/cities/**", "/countries/**", "/registration/**", "/users/**q")
                        .uri("lb://users-microservice"))
                .build();

    }
}
