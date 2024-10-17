package com.hexaware.hospitalmanagementsystem.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.hexaware.hospitalmanagementsystem.dao.IHospitalServiceImpl;
import com.hexaware.hospitalmanagementsystem.exception.AppointmentIdException;
import com.hexaware.hospitalmanagementsystem.exception.DoctorIdException;
import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;
import com.hexaware.hospitalmanagmentsystem.entity.Appointment;

public class HospitalService {

	IHospitalServiceImpl iHospitalService=new IHospitalServiceImpl(); // polymorphic object
	
	public Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException {
		return iHospitalService.getAppointmentById(aId);
	}
	public List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException {
		
		return iHospitalService.getAppointmentsForPatients(pId);
	}
	public List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException {
		return iHospitalService.getAppointmentsForDoctors(dId);
	}
	public boolean cancelAppointment(int aId) throws SQLException {
		return iHospitalService.cancelAppointment(aId);
	}
	public boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException {
		return iHospitalService.scheduleAppointment(pId,dId,appointmentDate,description);
	}
	public boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException {
		return iHospitalService.updateAppointment(aId,appointmentDate);
	}

}