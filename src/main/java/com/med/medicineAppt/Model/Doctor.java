package com.med.medicineAppt.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@JsonIgnoreProperties()
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctor_id;

	@NotEmpty
	@Size(min = 2, max = 24, message = "Doctor name require to have 2 or more characters")
	private String doctor_name;

	@NotEmpty
	@Past
	private LocalDate dob;

	@NotEmpty
	private String specialization;

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

//	public int getDoctor_id() {
//		return doctor_id;
//	}
//
//	public void setDoctor_id(int doctor_id) {
//		this.doctor_id = doctor_id;
//	}
//
//	public String getDoctor_name() {
//		return doctor_name;
//	}
//
//	public void setDoctor_name(String doctor_name) {
//		this.doctor_name = doctor_name;
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
//	public String getSpecialization() {
//		return specialization;
//	}
//
//	public void setSpecialization(String specialization) {
//		this.specialization = specialization;
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
