package com.med.medicineAppt.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//Useful in request on demand scenario
@JsonIgnoreProperties()
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patient_id;

	@NotEmpty
	@Size(min = 2, max = 24, message = "Minimum Character 2 maximum 24")
	private String patient_name;

	@NotEmpty
	@Past
	private LocalDate dob;

	@NotEmpty
	private String sex;

	@NotEmpty
	@Pattern(regexp = "[6-9]{1}[0-9]{9}")
	private String mobile_no;

	@NotEmpty
	@Size(min = 15, max = 100, message = "Door No, Street, Area, City, State")
	private String address;

	@NotEmpty
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;

	@NotEmpty
	private String login_password;

//	public int getPatient_id() {
//		return patient_id;
//	}
//
//	public void setPatient_id(int patient_id) {
//		this.patient_id = patient_id;
//	}
//
//	public String getPatient_name() {
//		return patient_name;
//	}
//
//	public void setPatient_name(String patient_name) {
//		this.patient_name = patient_name;
//	}
//
//	public LocalDate getDob() {
//		return dob;
//	}
//
//	public void setDob(LocalDate dob) {
//		this.dob = dob;
//	}
//
//	public String getSex() {
//		return sex;
//	}
//
//	public void setSex(String sex) {
//		this.sex = sex;
//	}
//
//	public String getMobile_no() {
//		return mobile_no;
//	}
//
//	public void setMobile_no(String mobile_no) {
//		this.mobile_no = mobile_no;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getLogin_password() {
//		return login_password;
//	}
//
//	public void setLogin_password(String login_password) {
//		this.login_password = login_password;
//	}

}
