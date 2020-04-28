package com.lhb.lhbauth.jwt.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * @ClassName: DemoController
 * @Description:TODO
 * @Auther: clj
 * @Date: 23/04/2019 14:15
 * @version : V1.0
 */
@RestController
public class DemoController {

    @RequestMapping(value="/demo/api",method= RequestMethod.POST)
    public String get() {
        return "ok";
    }
    @RequestMapping(value = "/hello/api",method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/testPermission",method = RequestMethod.POST)
    public String testPermission() {
        return "testPermission";
    }
}
