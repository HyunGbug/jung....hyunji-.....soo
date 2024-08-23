package com.emr.www.repository.doctor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emr.www.entity.doctor.DoctorEntity;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

	//관리자 - 중복체크
	boolean existsBySecurityNum(String securityNum);

	//로그인 및 회원가입
	Optional<DoctorEntity> findByLicenseId(String licenseId);

	Optional<DoctorEntity> findByLicenseIdAndPassword(String licenseId, String password);
	
    // no로 의사 정보를 조회하는 메서드
    Optional<DoctorEntity> findByNo(Long no);

	//수간호사
	@Query("SELECT d FROM DoctorEntity d WHERE (:name IS NULL OR d.name LIKE %:name%) AND (:position IS NULL OR d.position = :position)")
	List<DoctorEntity> searchDoctors(@Param("name") String name, @Param("position") String position);

	@Query("SELECT d FROM DoctorEntity d WHERE :name IS NULL OR d.name LIKE %:name%")
	List<DoctorEntity> findByNameContaining(@Param("name") String name);

	@Query("SELECT d FROM DoctorEntity d WHERE :position IS NULL OR d.position = :position")
	List<DoctorEntity> findByPosition(@Param("position") String position);
}
