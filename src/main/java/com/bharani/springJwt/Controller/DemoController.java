package com.bharani.springJwt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        return "hello world";
    }
    @GetMapping("/admin")
    public String admin(){
        return "hello admin";
    }
}
