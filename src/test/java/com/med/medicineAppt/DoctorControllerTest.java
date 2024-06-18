package com.med.medicineAppt;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.med.medicineAppt.Controller.DoctorController;
import com.med.medicineAppt.Model.Appointment;
import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Repository.AppointmentRepository;
import com.med.medicineAppt.Repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
public class DoctorControllerTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private DoctorController doctorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void testWelcome() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctor_id(1);
        doctor.setDoctor_name("Dr. John Doe");

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("doc_welcome"))
                .andExpect(model().attribute("id", 1))
                .andExpect(model().attribute("name", "Dr. John Doe"));
    }

    @Test
    public void testGetAppointmentCheck() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setDoctor_id(1);
        doctor.setDoctor_name("Dr. John Doe");

        Appointment appointment1 = new Appointment();
        appointment1.setAppointment_id(1);
        appointment1.setDoctorId(1);
        appointment1.setPatientId(1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointment_id(2);
        appointment2.setDoctorId(1);
        appointment2.setPatientId(2);

        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorId(1)).thenReturn(appointments);

        mockMvc.perform(get("/checkappointment/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("doc_appointment_check"))
                .andExpect(model().attribute("id", 1))
                .andExpect(model().attribute("name", "Dr. John Doe"))
                .andExpect(model().attribute("appointments", appointments));
    }

    @Test
    public void testGetDoctorDetails_success() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setDoctor_id(1);
        doctor1.setDoctor_name("John Doe");
        doctor1.setDob(LocalDate.of(1980, 1, 1));
        doctor1.setSpecialization("Cardiology");
        doctor1.setSex("Male");
        doctor1.setMobile_no("9876543210");
        doctor1.setAddress("123 Main St, City, State");
        doctor1.setEmail("john.doe@example.com");
        doctor1.setLogin_password("password123");

        Doctor doctor2 = new Doctor();
        doctor2.setDoctor_id(2);
        doctor2.setDoctor_name("Jane Doe");
        doctor2.setDob(LocalDate.of(1985, 2, 2));
        doctor2.setSpecialization("Neurology");
        doctor2.setSex("Female");
        doctor2.setMobile_no("9876543211");
        doctor2.setAddress("456 Another St, City, State");
        doctor2.setEmail("jane.doe@example.com");
        doctor2.setLogin_password("password456");

        given(doctorRepository.findAll()).willReturn(Arrays.asList(doctor1, doctor2));

        mockMvc.perform(get("/doctordetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("doctor_details"))
                .andExpect(model().attributeExists("doctors"));
    }

    @Test
    public void testGetDoctorDetails_exception() throws Exception {
        given(doctorRepository.findAll()).willThrow(new RuntimeException("Database error"));

        mockMvc.perform(get("/doctordetails"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error/"))
                .andExpect(flash().attribute("status", 500))
                .andExpect(flash().attribute("message", "Database error"));
    }
}

