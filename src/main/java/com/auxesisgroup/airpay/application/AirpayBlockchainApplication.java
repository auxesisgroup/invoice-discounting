package com.auxesisgroup.airpay.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "com.auxesisgroup.airpay" })
public class AirpayBlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirpayBlockchainApplication.class, args);
	}

}
