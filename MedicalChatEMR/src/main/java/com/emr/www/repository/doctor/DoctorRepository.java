package com.emr.www.repository.doctor;

import com.emr.www.entity.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // 필요한 경우 커스텀 메서드를 정의할 수 있습니다.
}
