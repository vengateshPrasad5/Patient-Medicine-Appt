package com.med.medicineAppt.Repository;

import com.med.medicineAppt.Model.Prescription;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Tag(name = "Prescription", description = "Prescription API")
@RepositoryRestResource(collectionResourceRel = "prescription", path = "prescription")
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

	public List<Prescription> findByPatientId(@Param("id") int id);

	public List<Prescription> findByDoctorId(@Param("id") int id);
}
