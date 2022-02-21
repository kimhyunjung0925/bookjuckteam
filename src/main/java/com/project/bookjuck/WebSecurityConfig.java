package com.project.bookjuck;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity  //SpringSecurityFilterChain이 자동으로 포함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/sendMail")
                .permitAll();

        http
                .authorizeRequests()
                    .antMatchers( "/css/**", "/js/**", "/upload/images/**"
                            , "/book/list", "/book/detail", "/book/detail_item","/cart","/main","/cscenter",
                            "/cscenter/faq", "/cscenter/notice","/ajax/cscenter/**",
                            "/user/join", "/user/idChk/**").permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .usernameParameter("uid")
                    .passwordParameter("upw")
                    .defaultSuccessUrl("/main")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/main")
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT uid as username, upw as password, 1 as enabled "
                        + " FROM t_user "
                        + " WHERE uid = ?")
                .authoritiesByUsernameQuery("SELECT uid, auth as authority "
                        + "FROM t_user "
                        + "WHERE uid = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}