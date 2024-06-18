package com.med.medicineAppt;

import com.med.medicineAppt.Model.Patient;
import com.med.medicineAppt.Repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PatientRepositoryTest {

	@Mock
	private PatientRepository patientRepository;

	Patient patient = new Patient();

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		patient.setPatient_name("Gen");
		patient.setDob(LocalDate.of(1990, 07, 03));
		patient.setSex("Male");
		patient.setMobile_no("9876543210");
		patient.setAddress("21 Adyar");
		patient.setEmail("user@email.com");
		patient.setLogin_password("12345");

		patientRepository.save(patient);
	}

	@Test
	public void getByName() {
		// Check if the searched patient name is same as the patient record that is
		String search_patient_name_1 = "Gen";
		Assertions.assertEquals(search_patient_name_1, patient.getPatient_name());
	}

}
