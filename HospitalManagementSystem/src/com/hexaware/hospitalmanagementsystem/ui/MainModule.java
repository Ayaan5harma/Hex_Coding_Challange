package com.hexaware.hospitalmanagementsystem.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.hexaware.hospitalmanagementsystem.exception.AppointmentIdException;
import com.hexaware.hospitalmanagementsystem.exception.DoctorIdException;
import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;
import com.hexaware.hospitalmanagementsystem.service.HospitalService;
import com.hexaware.hospitalmanagmentsystem.entity.Appointment;

public class MainModule {

	public static void main(String[] args) {
		HospitalService hospitalService=new HospitalService();
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("Press 1. Get appointment by appointment Id");
			System.out.println("Press 2. Get appointments for patient by patient Id");
			System.out.println("Press 3. Get appointments for doctor by doctor Id");
			System.out.println("Press 4. Schedule appointment");
			System.out.println("Press 5. Update appointment");
			System.out.println("Press 6. Cancel appointment");
			System.out.println("Press 0. Exit");
			int input=sc.nextInt();
			if(input==0)
			{
				System.out.println("Exiting... Thank You!");
				break;
			}
			switch(input)
			{
				case 1:
					System.out.println("Enter the appointment ID");
					int aId=sc.nextInt();
					try {
						Appointment appointment = hospitalService.getAppointmentById(aId);
						System.out.println("Appointment By appointment Id: "+aId);
						System.out.println("appointment_id "+" patient_id "+" doctor_id "+" appointment_date "+" description ");
						System.out.println("\t"+appointment.getAppointmentId()+"\t     "+appointment.getPatientId()+"\t\t"+appointment.getDoctorId()+"\t"+appointment.getAppointmentDate()+"\t"+appointment.getDescription());
						System.out.println();
					} catch (SQLException  e) {
						System.out.println(e.getMessage());
					}catch (AppointmentIdException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println("Enter the patient Id");
					int pId=sc.nextInt();
					try {
						List<Appointment> list = hospitalService.getAppointmentsForPatients(pId);
						System.out.println("Appointments for Patient "+pId);
						System.out.println("appointment_id "+" patient_id "+" doctor_id "+" appointment_date "+" description ");
						for(Appointment appointment:list)
						{
							System.out.println("\t"+appointment.getAppointmentId()+"\t     "+appointment.getPatientId()+"\t\t"+appointment.getDoctorId()+"\t"+appointment.getAppointmentDate()+"\t"+appointment.getDescription());
						}
						System.out.println();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					} catch ( PatientNumberNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Enter the doctor Id");
					int dId=sc.nextInt();
					try {
						List<Appointment> list = hospitalService.getAppointmentsForDoctors(dId);
						System.out.println("Appointments for Doctor "+dId);
						System.out.println("appointment_id "+" patient_id "+" doctor_id "+" appointment_date "+" description ");
						for(Appointment appointment:list)
						{
							System.out.println("\t"+appointment.getAppointmentId()+"\t     "+appointment.getPatientId()+"\t\t"+appointment.getDoctorId()+"\t"+appointment.getAppointmentDate()+"\t"+appointment.getDescription());
						}
						System.out.println();
					} catch (SQLException  e) {
						System.out.println(e.getMessage());
					}catch ( DoctorIdException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 4:
					System.out.println("Enter the patient Id");
					pId=sc.nextInt();
					System.out.println("Enter the doctor Id");
					dId=sc.nextInt();
					System.out.println("Enter the appointment date");
					String date=sc.next();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        LocalDate appointmentDate=LocalDate.parse(date, formatter);
			        System.out.println("Enter the description");
			        sc.nextLine();
					String description=sc.nextLine();
					boolean result;
					try {
						result = hospitalService.scheduleAppointment(pId, dId, appointmentDate, description);
						if(result)
						{
							System.out.println("Appointment made successfully");
						}
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 5:
					System.out.println("Enter the appointment Id");
					aId=sc.nextInt();
					System.out.println("Enter the updated date");
					date=sc.next();
					formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        appointmentDate=LocalDate.parse(date, formatter);
					try {
						result = hospitalService.updateAppointment(aId, appointmentDate);
						if(result)
						{
							System.out.println("Appointment updated successfully");
						}
					} catch (SQLException e1) {
						System.out.println(e1.getMessage());
					}
			        break;		
				case 6:
					System.out.println("Enter the appointment Id");
					aId=sc.nextInt();
					try {
						result = hospitalService.cancelAppointment(aId);
						if(result)
						{
							System.out.println("Appointment cancelled successfully");
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}
		}
		sc.close();
	}

}