package com.hexaware.hospitalmanagementsystem.dao;

import com.hexaware.hospitalmanagmentsystem.entity.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.hexaware.hospitalmanagementsystem.exception.AppointmentIdException;
import com.hexaware.hospitalmanagementsystem.exception.DoctorIdException;
import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;


public interface IHospitalService {

	Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException;

	List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException;

	List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException;

	boolean cancelAppointment(int aId) throws SQLException;

	boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException;

	boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException;

	
	
}
