package com.leocorp.springchat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@CrossOrigin
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

    /**
     * Function saying hello !
     * @return A string
     */
    @GetMapping("/hello")
    public String hello1() {
        return helloService.sayHello();
    }

    /**
     * Return a record
     *
     * @return A record of the type MyRecord
     */
    @GetMapping("/records")
    public MyRecord records() {
        return new MyRecord("toto", LocalDate.now());
    }
}
