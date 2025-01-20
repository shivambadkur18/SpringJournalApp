
package com.example.journalApp.Confg;
import com.example.journalApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
/*
@Configuration
@EnableWebSecurity
public class SpringSecurity {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http
                 .authorizeHttpRequests(request -> request
                        .requestMatchers("/public/createUser").permitAll()
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                         .httpBasic(Customizer.withDefaults())
                         .formLogin(Customizer.withDefaults())
                        .csrf(customizer -> customizer.disable());
                *//* .authorizeHttpRequests((authz) -> authz
                         .requestMatchers("/public/createUser").permitAll()
                         .anyRequest().authenticated())
                  .httpBasic( withDefaults())
                .csrf(customizer -> customizer.disable());*//*
        return  http.build();


      *//*  return http   //spring Security telusko securityConfig
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests( requests -> requests.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build() ;*//*

       // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    *//*@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider() ;
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(userDetailsService);
        return provider ;
    }*//*
}*/



@Configuration
@EnableWebSecurity
public class SpringSecurity {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

       return  http.authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll())

               .httpBasic(Customizer.withDefaults())
                 .build();
                //.formLogin(Customizer.withDefaults())

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}



