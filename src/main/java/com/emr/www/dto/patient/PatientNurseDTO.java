package com.emr.www.dto.patient;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//간호사 환자 관계 DTO (PatientNurseDTO)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientNurseDTO {

	private int no;
	private int patientId;
	private int nurseId;
	private LocalDate relationshipStartDate;
	private LocalDate relationshipEndDate;
}
	