public class ApplicationSecurityConfig 
{
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }
   /* .antMatchers("/register", 
		"/resources/**", 
		"/css/**", 
		"/fonts/**", 
		"/img/**", 
		"/js/**").permitAll()
.antMatchers("/users/addNew").permitAll();*/

}
