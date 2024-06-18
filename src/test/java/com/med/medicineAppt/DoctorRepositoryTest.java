package com.med.medicineAppt;

import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Repository.DoctorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class DoctorRepositoryTest {

	@Mock
	private DoctorRepository doctorRepository;

	Doctor doctor = new Doctor();

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		doctor.setDoctor_name("doctor name");
		doctor.setDob(LocalDate.of(1986, 11, 03));
		doctor.setSpecialization("Cardiothorasic");
		doctor.setSex("Male");
		doctor.setMobile_no("9874654320");
		doctor.setAddress("21 Nehru Street");
		doctor.setEmail("doc@email.com");
		doctor.setLogin_password("password");

		doctorRepository.save(doctor);
	}

	@Test
	public void getByName() {
		// Check if the searched doctor name is same as the doctor record that is
		String search_doctor_name_1 = "doctor name";
		Assertions.assertEquals(search_doctor_name_1, doctor.getDoctor_name());

	}

}
