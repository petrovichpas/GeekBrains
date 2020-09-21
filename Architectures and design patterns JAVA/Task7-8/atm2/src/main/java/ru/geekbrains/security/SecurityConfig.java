package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserAuthService(userRepository);
    }

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);

    }

    @Configuration
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/logs/**").hasAnyRole("ROLE_ADMIN")
                    .antMatchers("/video/**").hasAnyRole("ROLE_ADMIN")
                    .antMatchers("/deposit/**").hasAnyRole("ROLE_USER")
                    .antMatchers("/withdraw/**").hasAnyRole("ROLE_USER")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login")
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                    .logout().logoutSuccessUrl("/login")
                    .permitAll();
        }
    }
}
