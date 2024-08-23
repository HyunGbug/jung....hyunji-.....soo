package com.emr.www.repository.patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emr.www.entity.patient.MedicalRecordEntity;


public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Integer> {
    List<MedicalRecordEntity> findByPatientNo(int patientId);
}

