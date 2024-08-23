//package com.emr.www.entity.patient;
//
//import java.time.LocalDate;
//
//import com.emr.www.entity.doctor.DoctorEntity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
////의사 환자 관계 엔티티 (PatientDoctor)
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "PatientDoctor")
//public class PatientDoctorEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int no;
//
//	@ManyToOne
//	@JoinColumn(name = "patientId", referencedColumnName = "no", nullable = false)
//	private PatientRegistrationEntity patient;
//
//	@ManyToOne
//	@JoinColumn(name = "doctorId", referencedColumnName = "no", nullable = false)
//	private DoctorEntity doctor;
//
//	private LocalDate relationshipStartDate;
//	private LocalDate relationshipEndDate;
//}