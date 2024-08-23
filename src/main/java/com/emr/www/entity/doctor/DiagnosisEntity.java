package com.emr.www.entity.doctor;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Diagnosis")
public class DiagnosisEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;

	
	private String diseaseCode; //질병 코드
	private String diseaseName; //질병명

	// 기본 생성자
	public DiagnosisEntity() {
	}

	// 모든 필드를 매개변수로 받는 생성자
	public DiagnosisEntity(String diseaseCode, String diseaseName) {
		this.diseaseCode = diseaseCode;
		this.diseaseName = diseaseName;
	}

	// Getters and Setters
	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
}
