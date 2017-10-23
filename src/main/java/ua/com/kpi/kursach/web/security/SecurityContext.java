package ua.com.kpi.kursach.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityContext extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("*.html", "/css/**", "/js/**", "/images/**");

    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=Bad login")
                .defaultSuccessUrl("/loginSucesss")
                .usernameParameter("login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll().and().csrf().disable();
        
        
		http.sessionManagement()
		        .maximumSessions(50)
		        .expiredUrl("/login")
		        .and()
		        .invalidSessionUrl("/login");
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		                        .antMatchers("/student/**").hasRole("STUDENT")
		                        .antMatchers("/teacher/**").hasRole("TEACHER")
		                        .antMatchers("/admin/**").authenticated()
		                        .antMatchers("/student/**").authenticated()
		                        .antMatchers("/teacher/**").authenticated();
		
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

}
