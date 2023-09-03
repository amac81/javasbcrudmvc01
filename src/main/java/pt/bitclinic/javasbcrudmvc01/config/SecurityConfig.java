package pt.bitclinic.javasbcrudmvc01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		UserDetails jonh = User.builder().username("jonh").password("{noop}test123").roles("EMPLOYEE").build();

		UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER")
				.build();

		UserDetails susan = User.builder().username("susan").password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER", "ADMIN").build();

		return new InMemoryUserDetailsManager(jonh, mary, susan);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
				.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll());
		

		/*
		 * Cross-Site Request Forgery (CSRF) is an attack that forces authenticated
		 * users to submit a request to a Web application against which they are
		 * currently authenticated. CSRF attacks exploit the trust a Web application has
		 * in an authenticated user.
		 *
		 * In general CSRF protection is not required for stateless REST API's that use
		 * POST, PUT, DELETE, and/or PATCH.
		 *
		 */
		return http.build();
	}

	// Add support for JDBC

	/*
	 * @Bean UserDetailsManager userDetailsManager(DataSource dataSource) { return
	 * new JdbcUserDetailsManager(dataSource); }
	 * 
	 * @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 * http.authorizeHttpRequests(configurer -> configurer
	 * .requestMatchers(HttpMethod.GET, "/home").permitAll()
	 * 
	 * //.requestMatchers(HttpMethod.GET, "/api/clients").hasRole("EMPLOYEE")
	 * //.requestMatchers(HttpMethod.GET, "/api/clients/**").hasRole("EMPLOYEE")
	 * //.requestMatchers(HttpMethod.POST, "/api/clients").hasRole("MANAGER")
	 * //.requestMatchers(HttpMethod.PUT, "/api/clients/**").hasRole("MANAGER")
	 * //.requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasRole("ADMIN"));
	 * );
	 * 
	 * // Use HTTP basic authentication http.httpBasic(Customizer.withDefaults());
	 * 
	 * // Disable CSRF protection http.csrf(csrf -> csrf.disable());
	 * 
	 * /* Cross-Site Request Forgery (CSRF) is an attack that forces authenticated
	 * users to submit a request to a Web application against which they are
	 * currently authenticated. CSRF attacks exploit the trust a Web application has
	 * in an authenticated user.
	 *
	 * In general CSRF protection is not required for stateless REST API's that use
	 * POST, PUT, DELETE, and/or PATCH.
	 *
	 *
	 * return http.build(); }
	 */

}
