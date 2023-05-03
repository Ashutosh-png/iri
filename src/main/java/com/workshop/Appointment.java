package com.workshop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import ch.qos.logback.core.util.Duration;
import jakarta.persistence.Entity;
@Entity
public class Appointment {
	 private String hospitalName;
	    private String doctorName;
	    private Date appointmentDate;
	    private LocalTime appointmentTime;
	    private int timeRequired;
		public String getHospitalName() {
			return hospitalName;
		}
		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}
		public String getDoctorName() {
			return doctorName;
		}
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}
		public Date getAppointmentDate() {
			return appointmentDate;
		}
		public void setAppointmentDate(Date appointmentDate) {
			this.appointmentDate = appointmentDate;
		}
		public LocalTime getAppointmentTime() {
			return appointmentTime;
		}
		public void setAppointmentTime(LocalTime appointmentTime) {
			this.appointmentTime = appointmentTime;
		}
		public int getTimeRequired() {
			return timeRequired;
		}
		public void setTimeRequired(int timeRequired) {
			this.timeRequired = timeRequired;
		}
		public Appointment(String hospitalName, String doctorName, Date appointmentDate, LocalTime appointmentTime,
				int timeRequired) {
			super();
			this.hospitalName = hospitalName;
			this.doctorName = doctorName;
			this.appointmentDate = appointmentDate;
			this.appointmentTime = appointmentTime;
			this.timeRequired = timeRequired;
		}
		public Appointment() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Appointment [hospitalName=" + hospitalName + ", doctorName=" + doctorName + ", appointmentDate="
					+ appointmentDate + ", appointmentTime=" + appointmentTime + ", timeRequired=" + timeRequired + "]";
		}
	    
	    
	    

}
