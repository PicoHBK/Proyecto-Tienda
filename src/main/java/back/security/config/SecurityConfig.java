package back.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import back.security.filter.JwtRequestFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
  UserDetailsService usuarioDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }



  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(usuarioDetailsService);

    /*
    auth
        .inMemoryAuthentication()
        .withUser("jcabelloc").password("{noop}" + "secreto").roles("USER")
        .and()
        .withUser("mlopez").password("{noop}" + "secreto").roles("ADMIN");
     */
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        //.httpBasic(withDefaults())  // (1)
        .csrf().disable() // (2)
        .authorizeRequests()
        .antMatchers("/cargarUsuario/**","/producto/page/**","/producto/search/**","/producto/details/**").permitAll()
        .antMatchers("/test/**","/test3/**").hasRole("USER")
        .antMatchers("/producto/cargarcon/**","/producto/borrar/**").hasRole("ADMIN")
        .antMatchers("/test2/**").hasRole("ADMIN")
        .antMatchers("/authenticate/**").permitAll()
        .anyRequest().authenticated()
        .and().cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }





  


}
    
