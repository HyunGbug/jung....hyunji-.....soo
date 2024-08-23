package com.emr.www.dto.patient;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAssignmentDTO {
    private int no;
    private int patientId;
    private int doctorId;
    private int nurseId;
    private LocalDate assignmentDate;
}
