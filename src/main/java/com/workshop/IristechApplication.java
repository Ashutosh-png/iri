package com.workshop;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.opencsv.exceptions.CsvValidationException;

import ch.qos.logback.core.util.Duration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IristechApplication {

	public static void main(String[] args) throws CsvValidationException, IOException {
	SpringApplication.run(IristechApplication.class, args);
		
	
	        
	        
	        
	        
	}

}
