package com.med.medicineAppt.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "prescription")
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Prescription.PatientId", query = "from prescription where patientId =:id")
@NamedQuery(name = "Prescription.DoctorId", query = "from prescription where doctorId =:id")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prescription_id;

	@Column(name = "prescription_doctor_id")
	private int doctorId;

	@Column(name = "prescription_patient_id")
	private int patientId;

	@PastOrPresent
	@Column(name = "issued_date_time")
	private LocalDateTime issuedDateTime;
	
	private String findings;
	
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
	private String medicines;

//	public int getPrescription_id() {
//		return prescription_id;
//	}
//
//	public void setPrescription_id(int prescription_id) {
//		this.prescription_id = prescription_id;
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
//	public LocalDateTime getIssuedDateTime() {
//		return issuedDateTime;
//	}
//
//	public void setIssuedDateTime(LocalDateTime issuedDateTime) {
//		this.issuedDateTime = issuedDateTime;
//	}
//
//	public String getFindings() {
//		return findings;
//	}
//
//	public void setFindings(String findings) {
//		this.findings = findings;
//	}
//
//	public String getMedicines() {
//		return medicines;
//	}
//
//	public void setMedicines(String medicines) {
//		this.medicines = medicines;
//	}
	
}
