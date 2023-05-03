package com.workshop;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@org.springframework.stereotype.Service
public class Service {
	
	  private static     List<Appointment> appointments = new ArrayList<>();
	
	 public static List<String> readHospitalNames(String fileName) throws IOException, CsvValidationException {
	        List<String> hospitalNames = new ArrayList<>();
	        CSVReader reader = new CSVReader(new FileReader(fileName));
	        String[] nextLine;
	        while ((nextLine = reader.readNext()) != null) {
	            String hospitalName = nextLine[1];
	            hospitalNames.add(hospitalName);
	        } 
	        reader.close();
	        return hospitalNames;
	    }
	 

	 public static List<String> readDoctorNames(String fileName) throws IOException, CsvValidationException {
	        List<String> doctorNames = new ArrayList<>();
	        CSVReader reader = new CSVReader(new FileReader(fileName));
	        String[] nextLine;
	        while ((nextLine = reader.readNext()) != null) {
	            String hospitalName = nextLine[1];
	            doctorNames.add(hospitalName);
	        }
	        reader.close();
	        return doctorNames;
	    }
	 

	 public ResponseEntity<String> bookAppointment(Appointment appointment) throws CsvValidationException, IOException {

		 String hospitalfileName = "C:\\Users\\HP\\Desktop\\files\\Hospital.csv";
		    List<String> hospitalNames = readHospitalNames(hospitalfileName);
		 
		    String doctorfilename = "C:\\Users\\HP\\Desktop\\files\\Doctor.csv";
		    List<String> doctorNames = readDoctorNames(doctorfilename);
		    String hospitalName = appointment.getHospitalName();
		    String doctorName = appointment.getDoctorName();

		    if (!hospitalNames.contains(hospitalName) || !doctorNames.contains(doctorName)) {
		        System.out.println("Invalid data");
		        System.out.println();
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .header("Content-Type", "application/json")
		                .body("invalid data");
		    }
		        
		    List<String> bookedTimeSlots = new ArrayList<>();
		    List<String> allBookedTimeSlots = new ArrayList<>();

		    
		    for (Appointment existingAppointment : appointments) {
		        // check if appointment date and doctor are the same
		        if (existingAppointment.getDoctorName().equals(doctorName) &&
		                existingAppointment.getAppointmentDate().equals(appointment.getAppointmentDate())) {
		            // check if appointment times overlap
		            if (appointment.getAppointmentTime().isBefore(existingAppointment.getAppointmentTime().plusHours(existingAppointment.getTimeRequired())) &&
		                    existingAppointment.getAppointmentTime().isBefore(appointment.getAppointmentTime().plusHours(appointment.getTimeRequired()))) {
		                System.out.println("appointment overlaps with existing appointment");
		                allBookedTimeSlots.add(existingAppointment.getAppointmentTime().toString());

		                bookedTimeSlots.add(existingAppointment.getAppointmentTime().toString());

		               // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("appointment overlaps with existing appointment");
		            }else {
		                allBookedTimeSlots.add(existingAppointment.getAppointmentTime().toString());
		            }
		        }
		    
		    if (!bookedTimeSlots.isEmpty()) {
		    	 String message = "Appointment not available for the given date and time. The following time slots are already booked: ";
		    	    for (String time : allBookedTimeSlots) {
		    	        message += time + "-" + LocalTime.parse(time).plusHours(existingAppointment.getTimeRequired()) + ", ";
		    	    }
		    	    message = message.substring(0, message.length() - 2);
		    	    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
		    	}
		    }
		    
		    
		    
		    
		    
		    
		    
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(appointment.getAppointmentDate());
		    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		    if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
		        System.out.println("cannot book appointment on a weekend");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cannot book appointment on a weekend");
		    }

		    appointments.add(appointment);
		    System.out.println("added");
		    return ResponseEntity.status(HttpStatus.OK).body("added");
	     
		 
	 }


	public List<Appointment> showAll() {
		return appointments;
	}

}
