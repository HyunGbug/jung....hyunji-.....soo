package com.emr.www.dto.patient;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDTO {

	private int chartNum;
	private int patientId;
	private int docId;
	private String symptoms;
	private String surgeryStatus;
	private String progress;
	private LocalDateTime visitDate;
}
