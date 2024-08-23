package com.emr.www.dto.patient;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//의사 환자 관계 DTO (PatientDoctorDTO)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDoctorDTO {

	private int no;
	private int patientId;
	private int doctorId;
	private LocalDate relationshipStartDate;
	private LocalDate relationshipEndDate;
}
