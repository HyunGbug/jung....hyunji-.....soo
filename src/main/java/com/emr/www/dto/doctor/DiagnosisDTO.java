package com.emr.www.dto.doctor;


public class DiagnosisDTO {

	private String diseaseCode; //질병 코드
	private String diseaseName; //질병 명칭
	
	public String getDiseaseCode() {
		return diseaseCode;
	}
	
	public void setDisease_code(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	
	public void setDisease_name(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
	
}
