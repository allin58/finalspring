package by.training.cryptomarket.config;

import by.training.cryptomarket.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


/*@Configuration
@ComponentScan(basePackages = "by.training.cryptomarket")*/
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired()
    private UserDetailsServiceImpl userDetailsService;

  /*  @Bean
    public UserDetailsService userDetailsService() {

        return new UserDetailsServiceImpl();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.authorizeRequests().anyRequest().hasAnyRole("admin", "user");

        // через диалоговое окно
    //  http.authorizeRequests().anyRequest().hasAnyRole("admin", "user").antMatchers("/login","/login**", "/market**").permitAll();





        // Use Basic authentication
      // http.authorizeRequests().anyRequest().authenticated();

       /* http
                .authorizeRequests() // запросы авторизованые
                .anyRequest().permitAll();*/


                /*.anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll();*/

/*http
        .authorizeRequests()
        .anyRequest().authenticated().and()
        .formLogin().loginPage("/login").permitAll()
        .and().csrf().disable() // разрешить POST запросы
;*/



// всем разрешено всё
       /* http
                .authorizeRequests() // запросы авторизованые
                .anyRequest().permitAll().and().csrf().disable();*/

//логаут
        // http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


       /*  .antMatchers("/sec/*").hasRole("sec")
                .antMatchers("/market/*").hasRole("user")*/


      /*
       .antMatcher("/api/**")                               3
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()*/


     /*   http
                .authorizeRequests()

                .antMatchers("/registration/*","/market/*").permitAll()


               // .antMatchers("/sec/*").hasAnyRole("sec")



                .anyRequest().authenticated()

                .and()
               .formLogin().loginPage("/login").permitAll()
               // .formLogin().permitAll()
                .and()
                .csrf().disable();
*/

        http
                .authorizeRequests() // запросы авторизованые
                .anyRequest().permitAll().and().csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());

    }


    @Bean
    public MessageDigestPasswordEncoder getShaPasswordEncoder(){
        return new MessageDigestPasswordEncoder("SHA-256");
    }
}


