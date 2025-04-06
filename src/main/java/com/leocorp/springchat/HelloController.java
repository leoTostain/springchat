package com.leocorp.springchat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class HelloController {
    public record MyRecord(String name, LocalDate date) { }
    private final String toto;
    private final HelloService helloService;

    public HelloController(String toto, HelloService helloService) {
        this.toto = toto;
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String hello() {
        return toto;
    }

    @GetMapping("/hello")
    public String hello1() {
        return helloService.sayHello();
    }

    @GetMapping("/records")
    public MyRecord records() {
        return new MyRecord("toto", LocalDate.now());
    }
}
