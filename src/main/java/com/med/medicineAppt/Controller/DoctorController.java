package com.med.medicineAppt.Controller;

import com.med.medicineAppt.Model.Appointment;
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

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	PrescriptionRepository prescriptionRepository;

	// Doctor Landing Page
	@GetMapping({ "{docId}" })
	public String welcome(@PathVariable("docId") int docId, Model model) {

		String name = doctorRepository.findById(docId).get().getDoctor_name();
		model.addAttribute("id", docId);
		model.addAttribute("name", name);

		return "doc_welcome";
	}

	// Appointment Controller

	@GetMapping({ "checkappointment/{docId}" })
	public String getAppointmentCheck(@PathVariable("docId") int docId, Model model,
									  RedirectAttributes redirectAttributes) {

		try {
			String name = doctorRepository.findById(docId).get().getDoctor_name();
			model.addAttribute("id", docId);
			model.addAttribute("name", name);

			List<Appointment> appointments = appointmentRepository.findByDoctorId(docId);
			model.addAttribute("appointments", appointments);

			return "doc_appointment_check";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 404);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}


	// Prescription Controller

	@GetMapping({ "issueprescription/{appId}" })
	public String getPrescriptionIssue(@PathVariable("appId") int appId, Model model,
									   RedirectAttributes redirectAttributes ) {
		try {
			Appointment app = appointmentRepository.findById(appId).get();
			Prescription prescription = new Prescription();

			prescription.setDoctorId(app.getDoctorId());
			prescription.setPatientId(appId);
			prescription.setIssuedDateTime(LocalDateTime.now());
			model.addAttribute("prescriptionForm", prescription);

			int patientId = app.getPatientId();
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);

			return "doc_prescription_issue";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@PostMapping({ "issueprescription/save" })
	public String postPrescriptionIssue(@ModelAttribute Prescription prescription, Model model,
										RedirectAttributes redirectAttributes) {

		try {
			prescriptionRepository.save(prescription);
			return "redirect:/doctor/" + prescription.getDoctorId();
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@GetMapping({ "issuedprescription/{patientId}" })
	public String getPrescriptionIssued(@PathVariable("patientId") int patientId, Model model,
										RedirectAttributes redirectAttributes) {
		try {
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);

			List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
			System.out.println("prescriptions = " + prescriptions);
			model.addAttribute("prescriptions", prescriptions);

			return "doc_prescription_check";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@GetMapping({ "modifyprescription/{prescId}" })
	public String getPrescriptionModify(@PathVariable("prescId") int prescId, Model model,
										RedirectAttributes redirectAttributes) {
		try {
			int patientId = prescriptionRepository.findById(prescId).get().getPatientId();
			String name = patientRepository.findById(patientId).get().getPatient_name();
			model.addAttribute("id", patientId);
			model.addAttribute("name", name);

			Prescription prescription = prescriptionRepository.findById(prescId).get();
			prescription.setPrescription_id(prescId);
			prescription.setIssuedDateTime(LocalDateTime.now());
			model.addAttribute("modifyPrescriptionForm", prescription);

			return "doc_prescription_modify";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("status", 500);
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/error/";
		}
	}

	@PostMapping({ "modifyprescription/save" })
	public String postPrescriptionModify(@ModelAttribute("prescription") Prescription prescription) {

		prescriptionRepository.save(prescription);

		return "redirect:/doctor/" + prescription.getDoctorId();
	}

}
