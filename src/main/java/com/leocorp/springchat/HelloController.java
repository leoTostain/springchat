package com.leocorp.springchat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloController {
    /**
     * Homepage of the website
     * @return Hello World
     */
    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
