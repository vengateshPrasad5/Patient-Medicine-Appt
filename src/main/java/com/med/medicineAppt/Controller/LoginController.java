package com.med.medicineAppt.Controller;

import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Model.Patient;
import com.med.medicineAppt.Repository.DoctorRepository;
import com.med.medicineAppt.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientRepository patientRepository;

	@GetMapping({ "/" })
	public String loggedIn(@AuthenticationPrincipal User user, Model model) {

//		Based the username respective page will be redirected

		String username = user.getUsername();

		String url = "";
		if (doctorRepository.findByEmail(username) != null) {
			Doctor doc = doctorRepository.findByEmail(username);
			int docId = doc.getDoctor_id();
			url = "redirect:/doctor/" + docId;
		}

		else if (patientRepository.findByEmail(username) != null) {
			Patient pat = patientRepository.findByEmail(username);
			int patId = pat.getPatient_id();
			url = "redirect:/patient/" + patId;
		}

		return url;
	}
	


}
