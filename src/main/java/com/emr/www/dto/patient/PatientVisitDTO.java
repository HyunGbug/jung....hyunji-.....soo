package com.emr.www.dto.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientVisitDTO {

	private long no;
	private LocalDate visitDate;
	private LocalDateTime visitTime;
	private String patientName;
	private String securityNum;
	private String visitReason;
	private String doctorName;
	private String nurseName;
	private String visitHistory;
	private Long doctorNo;  // 추가
	private Long nurseNo;   // 추가
}
