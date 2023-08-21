package com.example.exchangeRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeRateApplication {
	// http://api.currencylayer.com/live?access_key=07cbae0088a84043cbd199ceb5c69cb3
	// https://github.com/coneseo/currencyconverter/tree/master/src/main/java/com/example/currencyconverter
	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}

}
