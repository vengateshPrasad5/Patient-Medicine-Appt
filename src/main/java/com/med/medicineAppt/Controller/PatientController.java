package com.med.medicineAppt.Controller;

import com.med.medicineAppt.Model.Appointment;
import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Model.Patient;
import com.med.medicineAppt.Model.Prescription;
import com.med.medicineAppt.Repository.AppointmentRepository;
import com.med.medicineAppt.Repository.DoctorRepository;
import com.med.medicineAppt.Repository.PatientRepository;
import com.med.medicineAppt.Repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("patient")
public class PatientController {
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	PrescriptionRepository prescriptionRepository;
	@Autowired
	DoctorRepository doctorRepository;

	@GetMapping({ "register" })
	public String register(Model model) {
		model.addAttribute("patientForm", new Patient());
		return "register";
	}

	@PostMapping({ "register/save" })
	public String postRegister(@ModelAttribute Patient patient, Model model) {
		patientRepository.save(patient);
		return "redirect:/login";
	}

	// Patient Landing Page
	@GetMapping({ "{patientId}" })
	public String welcome(@PathVariable("patientId") int patientId, Model model,
						  RedirectAttributes redirectAttributes) {
		try {
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);
			return "welcome";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@GetMapping({ "doctordetails" })
	public String getDoctorDetails(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<Doctor> doctors = doctorRepository.findAll();
			model.addAttribute("doctors", doctors);

			return "doctor_details";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	// Appointment Controller

	@GetMapping({ "bookappointment/{patientId}" })
	public String getPatientAppointmentBook(@PathVariable("patientId") int patientId, Model model,
											RedirectAttributes redirectAttributes) {
		try {
			Appointment appointment = new Appointment();
			appointment.setPatientId(patientId);

			Patient patient = patientRepository.findById(patientId).get();

			List<Doctor> doctors = doctorRepository.findAll();

			model.addAttribute("appointmentForm", appointment);
			model.addAttribute("doctors", doctors);
			model.addAttribute("patient", patient);

			return "appointment_book";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@PostMapping({ "bookappointment/save" })
	public String postAppointmentBook(@ModelAttribute Appointment appointment, Model model,
									  RedirectAttributes redirectAttributes) {

		try {
			appointmentRepository.save(appointment);

			int id = appointment.getPatientId();
			return "redirect:/patient/" + id;
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@GetMapping({ "checkappointment/{patientId}" })
	public String getAppointmentCheck(@PathVariable("patientId") int patientId, Model model,
									  RedirectAttributes redirectAttributes) {

		try {
			List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
			model.addAttribute("appointments", appointments);
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);

			return "appointment_check";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	// Prescription Controller

	@GetMapping({ "checkprescription/{patientId}" })
	public String getPrescriptionCheck(@PathVariable("patientId") int patientId, Model model,
									   RedirectAttributes redirectAttributes) {
		try {
			List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
			model.addAttribute("prescriptions", prescriptions);
			System.out.println("prescriptions = " + prescriptions);
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);
			return "prescription_check";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

}
