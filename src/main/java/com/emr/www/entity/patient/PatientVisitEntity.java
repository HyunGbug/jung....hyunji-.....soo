package com.emr.www.entity.patient;

import com.emr.www.entity.doctor.DoctorEntity;
import com.emr.www.entity.nurse.NurseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//환자 내원 테이블
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PatientVisits")
public class PatientVisitEntity {
	


    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

//    private LocalDate visitDate;
//    private LocalTime visitTime;
    private String patientName;
    private String securityNum;
    private String visitReason;
    private String doctorName;
    private String nurseName;
    private String visitHistory;

//    @ManyToOne
//    @JoinColumn(name = "securityNum", referencedColumnName = "securityNum")
//    private PatientRegistrationEntity patient;
    
    @ManyToOne
    @JoinColumn(name = "patientNo", referencedColumnName = "no")
    private PatientRegistrationEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctorNo", referencedColumnName = "no")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "nurseNo", referencedColumnName = "no")
    private NurseEntity nurse;
}