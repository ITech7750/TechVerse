package ru.itech.sno_api.core.config

import org.springframework.cloud.gateway.filter.factory.RetryGatewayFilterFactory.BackoffConfig
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.time.Duration

@Configuration
class GatewayConfig {
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration =
            CorsConfiguration().apply {
                addAllowedOriginPattern("*")
                addAllowedMethod("*")
                addAllowedHeader("*")
                allowCredentials = true
                maxAge = 3600L // maxAge в секундах
            }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator =
        builder
            .routes()
            .route("auth_route") { r ->
                r
                    .path("/auth/**")
                    .filters { f ->
                        f.retry { retryConfig ->
                            retryConfig
                                .setRetries(3)
                                .setStatuses(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                                .setMethods(org.springframework.http.HttpMethod.GET, org.springframework.http.HttpMethod.POST)
                                .setBackoff(
                                    BackoffConfig(
                                        Duration.ofMillis(500), // firstBackoff
                                        Duration.ofSeconds(2), // maxBackoff
                                        2, // factor
                                        true, // basedOnPreviousValue
                                    ),
                                )
                        }
                        f.circuitBreaker { cb ->
                            cb
                                .setName("authCircuitBreaker")
                                .setFallbackUri("forward:/fallback/auth")
                        }
                    }.uri("lb://auth-service")
            }.route("swagger_route") { r ->
                r
                    .path("/swagger-ui/**")
                    .uri("lb://auth-service")
            }.route("api-docs_route") { r ->
                r
                    .path("/v3/api-docs/**")
                    .uri("lb://auth-service")
            }.build()

    @Bean
    fun fallbackRouteLocator(builder: RouteLocatorBuilder): RouteLocator =
        builder
            .routes()
            .route("fallback_auth") { r ->
                r
                    .path("/fallback/auth")
                    .uri("forward:/auth-fallback")
            }.build()
}
