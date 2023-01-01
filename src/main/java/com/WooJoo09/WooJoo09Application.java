package com.WooJoo09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class WooJoo09Application {
//	@PostConstruct
//	void started() {
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//	}
	public static void main(String[] args) {
		SpringApplication.run(WooJoo09Application.class, args);
	}
}
