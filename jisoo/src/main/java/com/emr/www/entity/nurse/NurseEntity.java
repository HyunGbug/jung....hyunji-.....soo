package com.emr.www.entity.nurse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Nurse")
public class NurseEntity {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int no;

	    @Column
	    private String name;

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

		public String getLicenseId() {
			return licenseId;
		}

		public void setLicenseId(String licenseId) {
			this.licenseId = licenseId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
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

		public String getActiveStatus() {
			return activeStatus;
		}

		public void setActiveStatus(String activeStatus) {
			this.activeStatus = activeStatus;
		}

		@Column(unique = true) // 주민등록번호 (Unique Key)
	    private String securityNum;

	    @Column(unique = true)
	    private String email;

	    @Column
	    private String phone;

	    @Column(unique = true, length = 16)
	    private String licenseId;

	    @Column
	    private String password;
	    
	    private String position; 

	    @Column(length = 10)
	    private String departmentId;

	    private String profileImage;

	    @Column
	    private String activeStatus;
}