package com.lhb.lhbauth.jwt.demo.properties;

import lombok.Data;

 /**
  * @description:TODO
  * @author: clj
  * @date: 16:07 22/04/2019 
  * @version : V1.0
  */
@Data
public class ClientProperties {
    /** 授权客户端ID */
    private String clientId;
    /** 授权客户端密钥 */
    private String clientSecret;
}
