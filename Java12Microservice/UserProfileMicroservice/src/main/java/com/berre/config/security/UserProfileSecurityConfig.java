package com.berre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class UserProfileSecurityConfig {

   @Bean
   public JwtTokenFilter getJwtTokenFilter(){
       return new JwtTokenFilter();
   }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/mylogin.html").permitAll() //bu sayfaya gelene izin ver
//                .anyRequest().authenticated(); //her bir isteği otantikeden geçir
//        httpSecurity.formLogin().loginPage("/mylogin.html"); //istek gelince login sayfasına yönlendir.loginpage yazmazsan default login sayfası açılır




        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**","/v3/api-docs/**","/api/v1/user/findall").permitAll()
                .antMatchers("/api/v1/yetki/vip").hasAnyAuthority("VIP","User")
                .anyRequest().authenticated();

        //JwtTokenFilter ile kendi custom filterimizi yazdık
        httpSecurity.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
