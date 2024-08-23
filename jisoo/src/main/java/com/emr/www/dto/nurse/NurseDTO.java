package com.emr.www.dto.nurse;


public class NurseDTO {

	private int no; // int형 PK
	private String name; // 간호사 이름
	private String securityNum; // 주민등록번호
	private String email; // 이메일
	private String phone; // 전화번호
	private String licenseId; // 면허 ID
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecurityNum() {
		return securityNum;
	}
	public void setSecurityNum(String securityNum) {
		this.securityNum = securityNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String password; // 비밀번호
	private String position;// 직급 
	private String departmentId;  //진료과;
	private String profileImage; // 프로필 이미지 경로
	private String activeStatus; // 활동 상태 ('자리 비움', '진료 중', '점심시간')
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
}

