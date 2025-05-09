package backend.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {

            //Login
            "/api/users/login/**",

            //Register
            "/api/users/create/**",

            "/api/authorities/list",
            "/api/clients/create",
            "/api/events/list",
            "/api/cities/list",
            "/api/promoters/list"

    };

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Validar el Token
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //Configurar Conexiones Externas
        http.cors(Customizer.withDefaults()); //Permite request de servidores externos al del backend?
        http.csrf(AbstractHttpConfigurer::disable); //Permite reutilizar el token entre servidores?


        //Permisos para las rutas
        http.authorizeHttpRequests( auth-> auth
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(HttpMethod.POST,"/api/authorities/*").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.GET,"/api/authorities/").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers("/api/cities/**").hasAuthority("ADMIN")
                .requestMatchers("/api/promoters/**").hasAuthority("ADMIN")
                .requestMatchers("/api/expositor/**").hasAuthority("ADMIN")
                .requestMatchers("/api/sponsors/**").hasAuthority("ADMIN")
                .requestMatchers("/api/transactions/**").hasAnyAuthority("ADMIN", "USUARIO")

                .requestMatchers(HttpMethod.POST,"/api/clients/").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.GET,"/api/clients/").hasAnyAuthority("ADMIN")// ADMIN
                .requestMatchers(HttpMethod.POST,"/api/comments/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.GET,"/api/comments/").hasAnyAuthority("ADMIN") //ADMIN
                .requestMatchers(HttpMethod.POST,"/api/claims/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.GET,"/api/claims/").hasAnyAuthority("ADMIN")// ADMIN
                .requestMatchers(HttpMethod.POST,"/api/CulturalEvents/").hasAnyAuthority("ADMIN")// ADMIN
                .requestMatchers(HttpMethod.PUT,"/api/CulturalEvents/").hasAnyAuthority("ADMIN")// ADMIN
                .requestMatchers(HttpMethod.GET,"/api/CulturalEvents/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.POST,"/api/event-types/").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.GET,"/api/event-types/").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.POST,"/api/favorites/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.GET,"/api/favorites/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.POST,"/api/purchased-tickets/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.GET,"/api/purchased-tickets/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.POST,"/api/ticket-types/").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.GET,"/api/ticket-types/").hasAnyAuthority("USUARIO") // USUARIO
                .requestMatchers(HttpMethod.GET, "/api/users/list").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyAuthority("ADMIN") // ADMIN
                .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyAuthority("USUARIO")
                .requestMatchers(HttpMethod.GET, "/api/users/status").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
        );





        //Tipo de gestión de sesiones
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();

    }
}
