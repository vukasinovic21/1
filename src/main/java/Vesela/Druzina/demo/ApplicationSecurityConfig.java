/*package Vesela.Druzina.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter
{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    http
    .csrf().disable()
    /authorizeRequests()
    .antMatchers("/login","/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
    .antMatchers("/register","/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
    .antMatchers("/users/addNew").permitAll();
    .anyRequest().authenticated()
    .and()
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }

}
*/