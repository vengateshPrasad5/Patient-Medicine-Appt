package com.med.medicineAppt.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "appointment")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Appointment.PatientId", query = "from appointment where patientId =:id")
@NamedQuery(name = "Appointment.DoctorId", query = "from appointment where doctorId =:id")
@NamedQuery(name = "Appointment.DateSlot", query = "from appointment where doctorId =:id and visitDate =:vdate")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointment_id;

	@Column(name = "appointment_doctor_id")
	private int doctorId;

	@Column(name = "appointment_patient_id")
	private int patientId;

	@PastOrPresent
	@Column(name = "visit_date")
	private LocalDate visitDate;

	private int slot;

	private boolean booked;
//
//	public int getAppointment_id() {
//		return appointment_id;
//	}
//
//	public void setAppointment_id(int appointment_id) {
//		this.appointment_id = appointment_id;
//	}
//
//	public int getDoctorId() {
//		return doctorId;
//	}
//
//	public void setDoctorId(int doctorId) {
//		this.doctorId = doctorId;
//	}
//
//	public int getPatientId() {
//		return patientId;
//	}
//
//	public void setPatientId(int patientId) {
//		this.patientId = patientId;
//	}
//
//	public LocalDate getVisitDate() {
//		return visitDate;
//	}
//
//	public void setVisitDate(LocalDate visitDate) {
//		this.visitDate = visitDate;
//	}
//
//	public int getSlot() {
//		return slot;
//	}
//
//	public void setSlot(int slot) {
//		this.slot = slot;
//	}
//
//	public boolean isBooked() {
//		return booked;
//	}
//
//	public void setBooked(boolean booked) {
//		this.booked = booked;
//	}

}
