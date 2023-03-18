package com.example.networkinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan
public class NetworkInterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkInterfaceApplication.class, args);
	}

}
