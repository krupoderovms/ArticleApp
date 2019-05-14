package com.krupoderov.spring.articles.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity config) throws Exception {
        config
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/img").permitAll()
                .antMatchers("/editor").hasRole("EDITOR")
                .antMatchers("/editor/delete").hasRole("EDITOR")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/editor").permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication() // пользователь хранится в оперативной памяти
                .withUser("user").password("{noop}1").roles("EDITOR");
    }
}
