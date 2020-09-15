package com.Eragoo.Library.config;

import com.Eragoo.Library.security.AuthorizationFilterConfigurer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private AuthorizationFilterConfigurer filterConfigurer;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/swagger-ui/**")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitUrls = new String[]{"/api/auth"};
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permitUrls).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(filterConfigurer);
    }

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
