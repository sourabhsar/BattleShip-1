package com.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.demo.web","com.demo.config"})
public class BattleShipGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleShipGameApplication.class, args);
	}
}
