package com.seandtrent.messagingdashboard.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Provides access to Spring's Authentication Manager Builder and configures it.
 * The builder then goes on to automatically create an appropriate Authentication Manager.
 * @author Sean Trent trentsd
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  @Autowired
  DataSource dataSource;

  /**
   * Configures authentication.
   * Checks the provided DataSource object for user credentials.
   *
   * dataSource automatically points to the embedded H2 database.
   * The database is configured and populated by schema.sql and data.sql in main/resources.
   *
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery("select username,password,enabled "
        + "from users "
        + "where username = ?")
      .authoritiesByUsernameQuery("select username,authority "
        + "from authorities "
        + "where username = ?");
  }

  /**
   * Configures authorization.
   * Allows access to all of the static resources being built by angular to anyone.
   * This includes the home and login screens.
   * All other requests must be authenticated, but there is no other functionality yet.
   * Sends a CSRF token via a cookie in a header back to Angular.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .httpBasic()
    .and()
      .authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("ADMIN", "USER")
        .antMatchers("/index.html", "/*", "/home*", "/login*").permitAll()
        .anyRequest().authenticated()
    .and().csrf()
      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
  }

  /**
   * Password encoder requred by Spring Security.
   * For now, just using no-op encoder to satisfy requirement.
   * TODO: USE REAL ENCODER
   * @return an instance of a password encoder that can be picked up by Spring Security
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
