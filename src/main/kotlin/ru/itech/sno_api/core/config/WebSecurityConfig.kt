package ru.itech.sno_api.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import ru.itech.sno_api.core.AuthEntryPoint
import ru.itech.sno_api.core.AuthTokenFilter

@EnableWebSecurity
@Configuration
@Component
open class WebSecurityConfig {
    @Bean
    open fun provideAuthEntryPoint(): AuthenticationEntryPoint = AuthEntryPoint()

    @Bean
    open fun provideAuthTokenFilter(): AuthTokenFilter = AuthTokenFilter()

    @Bean
    open fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .formLogin {
                it.disable()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/auth/login",
                    "/api/auth/register",
                    "/api/**",
                    // Добавляем пути для Swagger UI
                    "/swagger-ui/**",  // URL для доступа к Swagger UI
                    "/v3/api-docs/**", // URL для Swagger API docs (OpenAPI)
                    "/swagger-ui.html",
                    "/favicon.ico",
                    "/webjars/**"     // URL для статических ресурсов, используемых Swagger UI


                ).permitAll()
                it.anyRequest().authenticated()
            }
            .exceptionHandling { exception ->
                exception.authenticationEntryPoint(
                    provideAuthEntryPoint(),
                )
            }
            .addFilterAfter(provideAuthTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}