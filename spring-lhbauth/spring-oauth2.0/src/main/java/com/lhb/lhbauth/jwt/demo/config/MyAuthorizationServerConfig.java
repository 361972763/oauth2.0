package com.lhb.lhbauth.jwt.demo.config;

import com.lhb.lhbauth.jwt.demo.properties.ClientLoadProperties;
import com.lhb.lhbauth.jwt.demo.properties.ClientProperties;
import com.lhb.lhbauth.jwt.demo.service.MyUserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.annotation.Resources;


/**
  * @description:TODO
  * @author: clj
  * @date: 16:07 22/04/2019
  * @version : V1.0
  */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Resource
    private ClientLoadProperties clientLoadProperties;
    @Value("${system.client.clientId}")
    private String clientId;
    @Value("${system.client.clientSecret}")
    private String clientSecret;
    @Autowired
    private AppConfig appConfig;

    /**
     * 定义token的存储方式
     *
     * @return TokenStore
     */
 /*   @Bean
    public TokenStore tokenStore() {

        return new RedisTokenStore(connectionFactory);
    }*/

    /**
     * 定义令牌端点上的安全性约束
     *
     * @param oauthServer oauthServer defines the security constraints on the token endpoint.
     * @throws Exception exception
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        oauthServer.allowFormAuthenticationForClients();

    }


    /**
     * 用于定义客户端详细信息服务的配置程序。可以初始化客户端详细信息，也可以只引用现有商店。
     *
     * @param clients a configurer that defines the client details service. Client details can be initialized, or you can just refer to an existing store.
     * @throws Exception exception
     */
 /*   @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        //修改
        ClientProperties [] pps = new ClientProperties[1];
        ClientProperties clientProperties = new ClientProperties();
        clientProperties.setClientId(clientId);
        clientProperties.setClientSecret(clientSecret);
        pps[0] = clientProperties;
        clientLoadProperties.setClients(pps);
        if (ArrayUtils.isNotEmpty(clientLoadProperties.getClients())) {
            for (ClientProperties config : clientLoadProperties.getClients()) {
                builder
                        //（必须）客户端id
                        .withClient(config.getClientId())
                        //（对于可信任的客户端是必须的）客户端的私密信息
                       // .secret(config.getClientSecret())
                        //设置token有效期
                        .accessTokenValiditySeconds(7 * 24 * 3600)
                        //设置refreshToken有效期
                        .refreshTokenValiditySeconds(7 * 24 * 3600)
                        //支持的认证方式
                        //.authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        .authorizedGrantTypes("refresh_token", "authorization_code")
                        //按需求设置为ture。默认允许
                        .autoApprove(true)
                        //授权域
                        .scopes("app","write");
            }
        }

    }*/


   /* @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailsService);
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(appConfig.tokenStore()); // Persist the tokens in the database
    }*/
    /**
    * @author clj
    * @description //数据库存储方式
    * @date 14:19 24/04/2019
    * @param [clients]
    * @return void
    **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(appConfig.dataSource());
    }

    /**
     * 定义授权和令牌端点以及令牌服务
     *
     * @param endpoints defines the authorization and token endpoints and the token services.
     * @throws Exception exception
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //指定认证管理器
                .authenticationManager(authenticationManager)
                //用户账号密码认证
                .userDetailsService(userDetailsService)
                // refresh_token
                .reuseRefreshTokens(false)
                //指定token存储位置  修改
                //.tokenStore(tokenStore());
                .tokenStore(appConfig.tokenStore());
    }

}
