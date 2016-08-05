package net.viotech.msstream.javaserver.configuration;

//import javax.inject.Inject;

import javax.inject.Inject;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	

	

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");
//	}
    
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/").permitAll()
		.and()
			.formLogin()
			.loginPage("/#/login")
			.loginProcessingUrl("/api/authentication")
			.usernameParameter("j_username")
			.passwordParameter("j_password")
			.permitAll()
		.and()
			.httpBasic()
        .and()
		     .logout()
		     .logoutUrl("/api/logout")
		     .deleteCookies("JSESSIONID")
		     .permitAll()
	    .and()
	     	.csrf()
	     	.disable();
	}


	protected void configure(AuthenticationManagerBuilder registry)
			throws Exception {
		
	}
	
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
      web.expressionHandler(handler);
    }
}
