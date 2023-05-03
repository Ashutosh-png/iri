package com.workshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.opencsv.exceptions.CsvValidationException;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private Service ser;
	
	@PostMapping("/bookAppointment")
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws CsvValidationException, IOException {
		 //  ser.bookAppointment(appointment);
		 try {
		        ResponseEntity<String> response = ser.bookAppointment(appointment);
		        return response;
		    } catch (IllegalArgumentException ex) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid data");
		    } catch (IllegalStateException ex) {
		        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("appointment overlaps with existing appointment");
		    } catch (RuntimeException ex) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot book appointment on a weekend");
		    }
	}
	
	
	
	
	@GetMapping("/showAppointment")
	public List<Appointment> showAll(){
		return   ser.showAll();
	}
	

}
