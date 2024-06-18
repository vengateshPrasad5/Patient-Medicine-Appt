package com.med.medicineAppt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.med.medicineAppt.Controller.PatientController;
import com.med.medicineAppt.Model.Appointment;
import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Model.Patient;
import com.med.medicineAppt.Repository.AppointmentRepository;
import com.med.medicineAppt.Repository.DoctorRepository;
import com.med.medicineAppt.Repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class PatientControllerTest {
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private Model model;
    @Mock
    private RedirectAttributes redirectAttributes;
    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPatientAppointmentBook_Success() {
        int pid = 1;
        Patient patient = new Patient();
        patient.setPatient_id(1);
        patient.setPatient_name("User1");
        patient.setDob(LocalDate.ofEpochDay(1995- 5 - 4));
        patient.setEmail("user@email.com");
        patient.setAddress("21 Bhujanar St");
        patient.setSex("male");

        Appointment appointment = new Appointment();
        appointment.setPatientId(1);

        List<Doctor> doctors = new ArrayList<>();

        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient));
        when(doctorRepository.findAll()).thenReturn(doctors);

        String viewName = patientController.getPatientAppointmentBook(pid, model, redirectAttributes);
//
        assertEquals("appointment_book", viewName);
        verify(model).addAttribute("appointmentForm", appointment);
        verify(model).addAttribute("doctors", doctors);
        verify(model).addAttribute("patient", patient);
    }
    @Test
    public void testPostAppointmentBook_Success() {
        Appointment appointment = new Appointment();
        appointment.setPatientId(1);
        appointment.setAppointment_id(1);
        appointment.setVisitDate(LocalDate.ofEpochDay(2024-6-18));
        appointment.setSlot(1);
        appointment.setBooked(true);

        String viewName = patientController.postAppointmentBook(appointment, model, redirectAttributes);

        assertEquals("redirect:/patient/1", viewName);
        verify(appointmentRepository).save(any(Appointment.class));
    }
    @Test
    public void testGetPatientAppointmentBook_Exception() {
        int patientId = 1;


        when(patientRepository.findById(anyInt())).thenThrow(new RuntimeException("Exception"));

        String viewName = patientController.getPatientAppointmentBook(patientId, model, redirectAttributes);

        assertEquals("redirect:/error/", viewName);
        verify(redirectAttributes).addFlashAttribute("status", 500);
        verify(redirectAttributes).addFlashAttribute("message", "Exception");
    }
}

