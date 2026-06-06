package javaman.testApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javaman.testApp.Services.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Autowired
    private MyUserDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                
                .authorizeHttpRequests(registry->{
                    registry.requestMatchers("/login/**").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.anyRequest().authenticated();

                })
                .formLogin(httpSecurityFormLoginConfigurer ->{
                	httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            .successHandler(new AuthenticationSuccessHandler())
                            .permitAll();
                })
                .logout(logoutConfigurer -> {
                    logoutConfigurer
                            .logoutUrl("/logout") 
                            .logoutSuccessUrl("/login?logout")
                            .invalidateHttpSession(true)       // Siguraduhing mawala ang session
                            .clearAuthentication(true)        // Linisin ang auth
                            .deleteCookies("JSESSIONID")      // Burahin ang cookie
                            .permitAll(); 
                })
             
                .build();
    }

 
    @Bean
    UserDetailsService userDetailsService(){
        return userDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}
