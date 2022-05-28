package ma.enset.studentsmvc.security;

import ma.enset.studentsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    private DataSource dataSource;*/

    @Autowired
    PasswordEncoder passwordEncode;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // a customized login page
        http.formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll();

        http.csrf().disable(); // disable CSRF prevention to use a logout link (via HTTP GET method)

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/styles/**", "/img/**").permitAll(); // permit static resources


        http.authorizeRequests().antMatchers("/delete/**", "/save/**", "/edit/**", "/formStudents/**", "/editStudents/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/index/**").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");

    }


}
