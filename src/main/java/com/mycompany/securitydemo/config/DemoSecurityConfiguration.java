package com.mycompany.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;

    //if u need custom login form, it will override the default by spring security
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/showMyLoginPage")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//                .and()
//                .logout().permitAll();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/managers/**").hasRole("MANAGER")
                .antMatchers("/admins/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }


//HOW OT DO LANDING PAGE WHICH IS ACCESSIBLE to EVERYONE ! + Role-URL matchers
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/myLandingPage").permitAll()  // allow public access to landing page
//                .antMatchers("/employees").hasRole("EMPLOYEE")
//                .antMatchers("/leaders/**").hasRole("MANAGER")
//                .antMatchers("/systems/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/showMyLoginPage")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")  // after logout then redirect to landing page (root)
//                .permitAll();
//    }


    // HOW TO CONFIGURE IN MEMORY USERS !!!!
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        //add users for in-memory auth
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("Nikolay").password("test").roles("EMPLOYEE"))
//                .withUser(userBuilder.username("Bogdan").password("test").roles("MANAGER","EMPLOYEE"))
//                .withUser(userBuilder.username("Evgeniy").password("test").roles("ADMIN", "EMPLOYEE","MANAGER"));
//
//
//    }

//USE JDBC TO AUTHENTICATE USERS

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }
}
