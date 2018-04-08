package com.promotion.winaball;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.promotion.winaball.context")
public class WinABallApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinABallApplication.class, args);
	}
}
