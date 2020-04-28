package com.lhb.lhbauth.jwt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

 /**
  * @description:web请求安全配置
  * @author: clj
  * @date: 16:07 22/04/2019 
  * @version : V1.0
  */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
    * @author clj
    * @description ////不定义没有password grant_type
    * @date 14:57 23/04/2019
    * @param []
    * @return org.springframework.security.authentication.AuthenticationManager
    **/
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
    * @author clj
    * @description  设置允许访问资源，
     * spingrecurity 默认对所有请求都添加了认证
    * @date 14:25 23/04/2019
    * @param [http]
    * @return void
    **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //表单登录
                .formLogin()
                //允许访问
                .and().authorizeRequests().antMatchers("/oauth/**,", "hello","/hello/**").permitAll().anyRequest().authenticated()
                //禁用跨站伪造
                .and().csrf().disable();

    }


}
