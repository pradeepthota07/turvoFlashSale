package com.turvo.flashsale;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashsaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashsaleApplication.class, args);
	}
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
	    return jacksonObjectMapperBuilder -> 
	        jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}

}
