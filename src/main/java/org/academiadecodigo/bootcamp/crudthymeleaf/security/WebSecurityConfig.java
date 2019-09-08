package org.academiadecodigo.bootcamp.crudthymeleaf.security;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void cofigure(HttpSecurity httpSecurity) {

        // secures all REST endpoints under "/employees/"

        // Why disable sessions?
        //
        // For our application, we would like avoid the use of cookies for session tracking. This should force the REST client
        // to enter user name and password for each request. However, this is not always the case depending on the REST client / browser
        // you are using. Your mileage will vary here (for example, this doesn't work in Eclipse embedded browser).

        // Why disable CSRF?
        //
        // Spring Security 5 has CSRF enabled by default. You would need to send over CSRF tokens.
        // However, CSRF generally does not apply for REST APIs. CSRF protection is a request that could be processed by a browser by normal users.
        // If you are only creating a REST service that is used by non-browser clients, you will likely want to disable
        try {
            httpSecurity
                    .authorizeRequests()
                        .antMatchers("/","employees/home").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .csrf().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                    .formLogin()
                        .loginPage("/employees/login")
                        .permitAll()
                        .and()
                    .logout()
                        .permitAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user =
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("pass")
                    .roles("USER")
                    .build();

        return new InMemoryUserDetailsManager(user);
    }
}

 */