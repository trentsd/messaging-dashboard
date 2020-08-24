package com.seandtrent.messagingdashboard;

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
   * dataSource automatically points to the embedded H2 database in accordance to Spring Security's defaults.
   * For now, we are creating a couple of test users here.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
      .dataSource(dataSource)
      .withDefaultSchema()
      .withUser(
        User.withUsername("user")
        .password("pass")
        .roles("USER")
      )
      .withUser(
        User.withUsername("admin")
        .password("pass")
        .roles("ADMIN")
      );
  }

  /**
   * Configures authorization.
   * Allows access to all of the static resources being built by angular to anyone.
   * All other requests must be authenticated, but there is no other functionality yet.
   * Sends a CSRF token via a cookie in a header back to Angular.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .httpBasic()
    .and()
      .authorizeRequests()
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
