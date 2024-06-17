package com.med.medicineAppt.Repository;

import com.med.medicineAppt.Model.Appointment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Appointment ", description = "Appointment API")
@RepositoryRestResource(collectionResourceRel = "appointment", path = "appointment")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findByPatientId(@Param("id") int id);

	public List<Appointment> findByDoctorId(@Param("id") int id);

	public List<Appointment> findByDoctorIdAndVisitDate(@Param("id") int id, @Param("vdate") LocalDate vdate);

}
