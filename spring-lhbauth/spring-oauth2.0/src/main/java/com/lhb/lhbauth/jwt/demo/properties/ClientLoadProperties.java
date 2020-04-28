package com.lhb.lhbauth.jwt.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

 /**
  * @description:TODO
  * @author: clj
  * @date: 16:07 22/04/2019
  * @version : V1.0
  */
@Component
@ConfigurationProperties("system.client")
public class ClientLoadProperties {
    private ClientProperties[] clients = {};

    public ClientProperties[] getClients() {
        return clients;
    }
    public void setClients(ClientProperties[] clients) {
            this.clients = clients;
    }
}
