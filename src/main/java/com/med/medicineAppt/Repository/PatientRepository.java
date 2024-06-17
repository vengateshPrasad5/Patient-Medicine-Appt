package com.med.medicineAppt.Repository;

import com.med.medicineAppt.Model.Patient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Tag(name = "Patient", description = "Patient API")
@RepositoryRestResource(collectionResourceRel = "patient", path="patient")
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public Patient findByEmail(@Param("email") String email);
}