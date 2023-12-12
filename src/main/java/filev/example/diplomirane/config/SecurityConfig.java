package filev.example.diplomirane.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "http://localhost:8080/realms/graduation_project";
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakAuthorityConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests
                        (authz -> authz
                                .requestMatchers("/prepodavatels/**").hasAuthority("Prepodavatel")
                                .requestMatchers( "/diplomnaZashtitas/**").hasAuthority("Prepodavatel")
                                .requestMatchers( "/zadanies/**").hasAuthority("Prepodavatel")
                                .requestMatchers("/students/**").hasAuthority("Prepodavatel")
                                .requestMatchers("/students/*").hasAuthority("Student")
                                .requestMatchers(HttpMethod.POST, "/students").hasAuthority("Student")

                                .anyRequest().authenticated()
                        )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtCustomizer -> jwtCustomizer
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }


}

