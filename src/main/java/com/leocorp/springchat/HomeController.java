package com.leocorp.springchat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/greetings")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}
