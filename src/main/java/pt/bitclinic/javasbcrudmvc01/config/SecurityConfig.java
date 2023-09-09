package pt.bitclinic.javasbcrudmvc01.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	// Add support for JDBC

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
				.requestMatchers("/").hasRole("EMPLOYEE")
				
				.requestMatchers("/employees/delete/**").hasRole("MANAGER")
				.requestMatchers("/employees/edit/**").hasRole("MANAGER")
				.requestMatchers("/employees/showDetails/**").hasRole("MANAGER")
				.requestMatchers("/employees/saveDetails/**").hasRole("MANAGER")
				.requestMatchers("/employees/showFormForAdd/**").hasRole("MANAGER")
				.requestMatchers("/employees/showFormForUpdate/**").hasRole("MANAGER")
				
				.requestMatchers("/employees/leaders/**").hasRole("MANAGER")
				.requestMatchers("/employees/systems/**").hasRole("ADMIN")
				
				.requestMatchers("/projects/delete/**").hasRole("MANAGER")
				.requestMatchers("/projects/edit/**").hasRole("MANAGER")
				.requestMatchers("/projects/showTasks/**").hasRole("MANAGER")
				.requestMatchers("/projects/showFormForAdd/**").hasRole("MANAGER")
				.requestMatchers("/projects/showFormForUpdate/**").hasRole("MANAGER")
				
				.requestMatchers("/tasks/delete/**").hasRole("MANAGER")
				.requestMatchers("/tasks/edit/**").hasRole("MANAGER")
				.requestMatchers("/tasks/showFormForAdd/**").hasRole("MANAGER")
				.requestMatchers("/tasks/showFormForUpdate/**").hasRole("MANAGER")
				
				.requestMatchers("/teams/delete/**").hasRole("MANAGER")
				.requestMatchers("/teams/edit/**").hasRole("MANAGER")
				.requestMatchers("/teams/showMembers/**").hasRole("MANAGER")
				.requestMatchers("/teams/showFormForAdd/**").hasRole("MANAGER")
				.requestMatchers("/teams/showFormForUpdate/**").hasRole("MANAGER")
				
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser")
						.permitAll())
				.logout(logout -> logout.permitAll())
				.exceptionHandling(configurer -> configurer.accessDeniedPage("/denied"));

		return http.build();
	}
	
	/*
	 * In memory users
	 * 
	 * @Bean InMemoryUserDetailsManager userDetailsManager() { UserDetails jonh =
	 * User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").
	 * build();
	 * 
	 * UserDetails mary =
	 * User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE",
	 * "MANAGER") .build();
	 * 
	 * UserDetails susan =
	 * User.builder().username("susan").password("{noop}test123") .roles("EMPLOYEE",
	 * "MANAGER", "ADMIN").build();
	 * 
	 * UserDetails amac = User.builder().username("amac").password("{noop}test123")
	 * .roles("EMPLOYEE", "ADMIN").build();
	 * 
	 * return new InMemoryUserDetailsManager(jonh, mary, susan, amac); }
	 */

}
