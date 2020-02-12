package com.carrentalooo.carrentalmytest;
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


*/
/**
 * @author Tika Raj
 *
 *//*

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	*/
/*@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;*//*

*/
/*
	public void setMd5PasswordEncoder(Md5PasswordEncoder md5PasswordEncoder) {
		this.md5PasswordEncoder = md5PasswordEncoder;
	}*//*


	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

	 
	auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new Md5PasswordEncoder())
		.usersByUsernameQuery(
				"select username as username, password as password, active as active from account where username=?")
		.authoritiesByUsernameQuery(
				"select username as username, account_type as role from account where username=?");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll()
		.antMatchers("/","/login/**","/user/**").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/admin/**")
				.hasAnyRole("CUSTOMER, ADMIN").antMatchers("/user")
				.permitAll().antMatchers("/login/addUser").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login")
				.successForwardUrl("/login/handleLogin").permitAll().and().logout().permitAll().and().sessionManagement().invalidSessionUrl("/login");
	}
 

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resource/**");
	}
}
*/
