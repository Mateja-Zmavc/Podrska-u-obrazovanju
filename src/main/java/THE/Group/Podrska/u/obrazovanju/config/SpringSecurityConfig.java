package THE.Group.Podrska.u.obrazovanju.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("mojKorisnikDetaljiService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
                http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/css/**","/js/**","fonts/**", "/css/bootstrap/**", "/images/**").permitAll()
                .antMatchers( "/admin-pocetna","/korisnik-lista","/korisnik-prikazi","/korisnik-izmeni", "/korisnik-obrisi", "/ustanove","/ustanove-lista","/ustanove-prikazi/{ustanoveID}", "/ustanove-izmeni/{ustanoveID}", "/ustanove-novi", "/ustanove-sacuvaj", "/ustanove-obrisi/{ustanoveID}","/admin-oglas-lista", "/admin-oglas-izmeni/{oglasID}", "/admin-oglas-obrisi/{oglasID}", "/admin-oglas-sacuvajIzmene").hasAuthority("admin")
                        .antMatchers("/ustanova-pocetna","/ustanova-oglas-lista","/ustanova-oglas-novi", "/ustanova-oglas-izmeni/{oglasID}", "/ustanova-oglas-obrisi/{oglasID}","/ustanova-oglas-sacuvaj", "/ustanova-oglas-sacuvajIzmene").hasAuthority("ustanova")
                        .antMatchers("/index","/oglas", "/oglasi-prikazi", "/oglas-prikazi/{oglasID}", "/mailingLista-sacuvaj/{ustanoveID}").hasAuthority("korisnik")
                .antMatchers("/","/korisnik-novi","/korisnik-registracija","/korisnik-sacuvaj", "/login","/logout","/send", "/new", "/result", "/emailTemplate", "/message", "/forgotPass", "/salji-pass").permitAll()
                        .anyRequest().authenticated()
                 .and()
               .formLogin()
               .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
              .permitAll()
              .and()

              .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login");
    }

}

