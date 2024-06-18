package com.med.medicineAppt.Config;

import com.med.medicineAppt.Model.Doctor;
import com.med.medicineAppt.Model.Patient;
import com.med.medicineAppt.Repository.DoctorRepository;
import com.med.medicineAppt.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // 1 Admin
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();

        // 2. User
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("12345"))
                .roles("USER")
                .build();


        List<Doctor> docs = doctorRepository.findAll();
        List<Patient> pats = patientRepository.findAll();

        List<UserDetails> doctors = new ArrayList<>();
        List<UserDetails> patients = new ArrayList<>();

        // 3. Doctors
        for(Doctor doc: docs) {
            doctors.add(User.withUsername(doc.getEmail())
                    .password(encoder.encode(doc.getLogin_password()))
                    .roles("USER")
                    .build()
            );
        }


        // 4. Patients

        for(Patient patient: pats) {
            patients.add(User.withUsername(patient.getEmail())
                    .password(encoder.encode(patient.getLogin_password()))
                    .roles("USER")
                    .build()
            );
        }

        // Adding doctors and patients into InMemoryUserDetails
        Collection<UserDetails> allUsers = new ArrayList<>();
        allUsers.addAll(doctors);
        allUsers.addAll(patients);

        return new InMemoryUserDetailsManager(allUsers);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable) //Replaced with lambda reference
                .formLogin(withDefaults())
                .build();

    }
}
