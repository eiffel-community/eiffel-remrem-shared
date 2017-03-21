package com.ericsson.eiffel.remrem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("!integration-test")
@Configuration
@ConditionalOnProperty(value = "activedirectory.enabled")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${activedirectory.enabled}")
    private boolean securityEnabled;

    @Value("${activedirectory.ldapUrl}")
    private String ldapUrl;

    @Value("${activedirectory.ldapPassword}")
    private String ldapPassword;

    @Value("${activedirectory.managerDn}")
    private String managerDn;

    @Value("${activedirectory.userSearchFilter}")
    private String userSearchFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication().userSearchFilter(userSearchFilter).contextSource().managerDn(managerDn)
                .managerPassword(ldapPassword).url(ldapUrl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
    }
}
