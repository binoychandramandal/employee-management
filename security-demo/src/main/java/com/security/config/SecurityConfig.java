package com.security.config;

import com.security.jwt.JwtFilter;
import com.security.spring.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter filter;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                // .antMatchers("/api/test/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                // .antMatchers("/api/test/**").hasAnyRole("ADMIN","USER")
                // .antMatchers("/api/home/**").hasRole("ADMIN")
                // .antMatchers(HttpMethod.GET).permitAll()
                //.antMatchers(HttpMethod.POST).permitAll()
//          .anyRequest().authenticated().and().formLogin()
//                .loginPage("/api/auth/login")
//                .loginProcessingUrl("/login")
//       .defaultSuccessUrl("/api/home/");

                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("rabin").password(getEncoder().encode("rabin")).roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("dipen").password(getEncoder().encode("dipen")).roles("U");
        auth.userDetailsService(userDetailsService).passwordEncoder(getEncoder());
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
