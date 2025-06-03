package com.nanhng.FastFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.scheduling.annotation.EnableAsync;

@EntityScan(basePackageClasses = {FastFoodApplication.class, Jsr310Converters.class})
@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication
public class FastFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFoodApplication.class, args);
	}

}
