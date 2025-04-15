package com.leocorp.springchat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HomeController {

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}
