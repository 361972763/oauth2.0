package com.lhb.lhbauth.jwt.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 /**
  * @description:TODO
  * @author: clj
  * @date: 16:08 22/04/2019 
  * @version : V1.0
  */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "你已经访问";
    }
}
