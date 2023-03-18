package com.example.networkinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.networkinterface")
public class NetworkInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkInterfaceApplication.class, args);
	}

}
