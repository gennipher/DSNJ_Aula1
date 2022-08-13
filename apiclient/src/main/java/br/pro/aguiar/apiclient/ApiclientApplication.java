package br.pro.aguiar.apiclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(
			ApiclientApplication.class, args);
	}
}
