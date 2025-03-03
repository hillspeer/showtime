package org.test.showtime.config;

import org.test.showtime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.test.showtime.service.UserService;

@Configuration
@ComponentScan(basePackages = "org.test.showtime")
@EnableJpaRepositories(basePackages = "org.test.showtime.repository")
@EnableJpaAuditing
@EnableWebMvc
@EnableWebSecurity
class ApplicationConfiguration {

    @Autowired
    UserService userService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    AuthenticationManager authenticationManager;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        authenticationManager =authConfig.getAuthenticationManager();
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration(){
//                        {
//                            setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Add your client URL here
//                            setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                            setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Custom-Header"));
//                        }
//                    };
//                    return config;
//                }))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        Set permissions on endpoints
                .authorizeHttpRequests(auth -> auth
//            our public endpoints
                        .requestMatchers(HttpMethod.POST, "/location/**", "/booking/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/show/**", "/screen/**", "/booking/**","/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/theatre/**","/error/**" ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/theatre/**", "/show/**", "/screen/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/save/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "**/authentication-docs/**").permitAll()
//            our private endpoints
                        .anyRequest().authenticated())
                //.authenticationManager(authenticationManager)

//        We need jwt filter before the UsernamePasswordAuthenticationFilter.
//        Since we need every request to be authenticated before going through spring security filter.
//        (UsernamePasswordAuthenticationFilter creates a UsernamePasswordAuthenticationToken from a username and password that are submitted in the HttpServletRequest.)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}