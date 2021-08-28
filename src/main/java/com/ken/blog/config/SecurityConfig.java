package com.ken.blog.config;


import com.ken.blog.config.auth.PrincipalDetail;
import com.ken.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// register bean : manage object in spring container
@Configuration // register bean(loC)
@EnableWebSecurity // add security filter = spring security is active, you can add more configuration into spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) //access to specific address and pre-check authentication, authorization
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean // loC
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // spring security will log in instead of user, also intercepts password
    // check what hash code of password is when it's sign up
    // compare same hash code between db data and input data
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // csrf token unable (when you test setup unable this)
            .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/");   // spring security intercepts login logic and login
                // .failureUrl()
    }
}
