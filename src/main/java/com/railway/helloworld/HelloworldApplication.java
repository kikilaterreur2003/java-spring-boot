package com.railway.helloworld;

import com.railway.helloworld.controller.parser.ArithmeticParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloworldApplication.class, args);

	/*
		String expression = "3+5*(2-8)";
		ArithmeticParser parser = new ArithmeticParser(expression);
		try {
			int result = parser.parse();
			System.out.println("Result: " + result);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}*/
	}



}
