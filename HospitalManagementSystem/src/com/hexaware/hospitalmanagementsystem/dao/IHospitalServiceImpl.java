package com.hexaware.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.hospitalmanagementsystem.exception.AppointmentIdException;
import com.hexaware.hospitalmanagementsystem.exception.DoctorIdException;
import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;
import com.hexaware.hospitalmanagmentsystem.entity.Appointment;
import com.hexaware.hospitalmanagementsystem.util.*;

public class IHospitalServiceImpl implements IHospitalService{

	@Override
	public Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException {
		Connection conn=DBConnection.getDBConn();
		
		String sql="select * from appointment where appointmentId=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, aId);
		
		ResultSet rst=pstmt.executeQuery();
		
		if(rst.next())
		{
			int appointmentId=rst.getInt("appointmentId");
			int patientId=rst.getInt("patientId");
			int doctorId=rst.getInt("doctorId");
			LocalDate appointmentDate=rst.getDate("appointmentDate").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			return appointment;
		}
		
		DBConnection.dbClose();
		throw new AppointmentIdException("Invalid appointment Id");
	}
	@Override
	public List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException {
		Connection conn=DBConnection.getDBConn();
		List<Appointment> list=new ArrayList<>();
		String sql="select * from appointment where patientId=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, pId);
		
		ResultSet rst=pstmt.executeQuery();
		while(rst.next())
		{
			int appointmentId=rst.getInt("appointmentId");
			int patientId=rst.getInt("patientId");
			int doctorId=rst.getInt("doctorId");
			LocalDate appointmentDate=rst.getDate("appointmentDate").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			list.add(appointment);
		}
		DBConnection.dbClose();
		if(list.isEmpty())
		{
			throw new PatientNumberNotFoundException("Invaid patient Id");
		}
		return list;
	}
	@Override
	public List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException {
		Connection conn=DBConnection.getDBConn();
		List<Appointment> list=new ArrayList<>();
		String sql="select * from appointment where patientId=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, dId);
		
		ResultSet rst=pstmt.executeQuery();
		while(rst.next())
		{
			int appointmentId=rst.getInt("appointmentId");
			int patientId=rst.getInt("patientId");
			int doctorId=rst.getInt("doctorId");
			LocalDate appointmentDate=rst.getDate("appointmentDate").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			list.add(appointment);
		}
		DBConnection.dbClose();
		if(list.isEmpty())
		{
			throw new DoctorIdException("Invaid doctor Id");
		}
		return list;

	}
	
	@Override
	public boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException {
		Connection conn=DBConnection.getDBConn();
		String sql="insert into appointment(patientId,doctorId,appointmentDate,description) values (?,?,?,?)";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, pId);
		pstmt.setInt(2, dId);
		pstmt.setObject(3, appointmentDate);
		pstmt.setString(4, description);
		
		int res=pstmt.executeUpdate();
		DBConnection.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException {
		Connection conn=DBConnection.getDBConn();
		String sql="update appointment set appointmentDate=? where appointmentId=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setObject(1,appointmentDate);
		pstmt.setInt(2, aId);
		
		int res=pstmt.executeUpdate();
		DBConnection.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	public boolean cancelAppointment(int aId) throws SQLException {
		Connection conn=DBConnection.getDBConn();
		String sql="delete from appointment where appointmentId=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, aId);
		
		int res=pstmt.executeUpdate();
		DBConnection.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	
}