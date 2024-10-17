package com.hexaware.hospitalmanagmentsystem.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Appointment {

	private int appointmentId;
	private int patientId;
	private int doctorId;
	private LocalDate appointmentDate;
	private String description;
	
	public Appointment() {}

	public Appointment(int appointmentId, int patientId, int doctorId, LocalDate appointmentDate, String description) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.description = description;
	}

	public Appointment(int patientId, int doctorId, LocalDate appointmentDate, String description) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.description = description;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", appointmentDate=" + appointmentDate + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentDate, appointmentId, description, doctorId, patientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentDate, other.appointmentDate) && appointmentId == other.appointmentId
				&& Objects.equals(description, other.description) && doctorId == other.doctorId
				&& patientId == other.patientId;
	}
	
	
	
	
	
	
}