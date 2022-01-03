package ro.unibuc.fmi.dietapp.backend.config;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.unibuc.fmi.dietapp.backend.filter.JwtAuthenticationFilter;

@Configuration
public class ProjectConfig {
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder)
    {
        return routeLocatorBuilder.routes()
                .route("authentication-microservice", rt-> rt.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://authentication-microservice"))
                .route("diets-microservice", rt-> rt.path("/diets/**", "/foods/**", "/ingredients/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://diets-microservice"))
                .route("optimums-microservice", rt-> rt.path("/optimums/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://optimums-microservice"))
                .route("payments-microservice", rt-> rt.path("/payments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://payments-microservice"))
                .route("users-microservice", rt-> rt.path("/accounts/**","/cities/**", "/happiness/**","/registration/**", "/users/**", "/weights/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://users-microservice"))
                .build();

    }
}
