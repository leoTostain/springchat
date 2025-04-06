package com.leocorp.springchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringchatApplication.class, args);
	}

	@Bean
	public String toto() {
		return "He<b>llo</b> World<br/>terst <script>alert('hello');</script>";
	}

}
