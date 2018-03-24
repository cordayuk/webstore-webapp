package com.fakeshop.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    public static class CustomerSecurtiyConfig extends WebSecurityConfigurerAdapter {

        public CustomerSecurtiyConfig(){super();}

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/account/**").hasRole("CUSTOMER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout().permitAll();


            // Security configuration to allow in memory h2-console to be accessed
            http
                    .authorizeRequests()
                    .antMatchers("/console/**").permitAll()
                    .and()
                    .headers().frameOptions().disable()
                    .and()
                    .csrf().disable();
            }
    }

}
