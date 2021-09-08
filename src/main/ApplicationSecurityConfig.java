/*public class ApplicationSecurityConfig 
{
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    http
    .antMatchers("/login","/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
    .antMatchers("/register","/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
    .antMatchers("/users/addNew").permitAll();
    }

}
*/