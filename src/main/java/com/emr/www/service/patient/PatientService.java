package com.emr.www.service.patient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emr.www.dto.patient.PatientDTO;
import com.emr.www.dto.patient.PatientVisitDTO;
import com.emr.www.entity.doctor.DoctorEntity;
import com.emr.www.entity.nurse.NurseEntity;
import com.emr.www.entity.patient.PatientRegistrationEntity;
import com.emr.www.entity.patient.PatientVisitEntity;
import com.emr.www.repository.doctor.DoctorRepository;
import com.emr.www.repository.nurse.NurseRepository;
import com.emr.www.repository.patient.PatientRegistrationRepository;
import com.emr.www.repository.patient.PatientVisitRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRegistrationRepository patientRepository;
	
    @Autowired
    private PatientVisitRepository patientVisitRepository;
    
	@Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

	private static final Logger log = LoggerFactory.getLogger(PatientService.class);

	// 주민등록번호 중복 여부 확인 메서드
	public boolean isSecurityNumExists(String securityNum) {
		return patientRepository.findBySecurityNum(securityNum) != null;
	}

	@Transactional
	public PatientRegistrationEntity registerPatient(PatientDTO patientDTO) {
		log.info("DTO로 환자 등록 프로세스 시작 : {}\n", patientDTO);

		// 이름 중복 체크 및 수정
		String originalName = patientDTO.getName();
		String uniqueName = getUniqueName(originalName);
		patientDTO.setName(uniqueName);

		// DTO를 엔티티로 변환
		PatientRegistrationEntity patient = new PatientRegistrationEntity();
//		BeanUtils.copyProperties(patientDTO, patient);
        // 'no' 필드를 제외하고 복사
		BeanUtils.copyProperties(patientDTO, patient, "no"); // 'no' 필드를 제외한 나머지 필드 복사

		// no를 null로 설정하여 BeanUtils.copyProperties 호출 시 문제가 발생하지 않도록 함
//        patientDTO.setNo(null);
        


		log.info("PatientDTO를 Patient 엔티티로 변환 : {}\n", patient);

		// 환자 정보 저장
		PatientRegistrationEntity savedPatient = patientRepository.save(patient);
		log.info("환자가 성공적으로 저장되었습니다. 생성된 ID : {}\n", savedPatient.getNo());
		return savedPatient;
	}

	public PatientDTO getPatientBySecurityNum(String securityNum) {
		PatientRegistrationEntity patient = patientRepository.findBySecurityNum(securityNum);
		if (patient != null) {
			PatientDTO patientDTO = new PatientDTO();
			BeanUtils.copyProperties(patient, patientDTO);
			return patientDTO;
		}
		return null;
	}
	
    private String getUniqueName(String name) {
        // 원본 이름으로 시작하는 모든 환자 이름을 조회합니다.
        List<PatientRegistrationEntity> existingPatients = patientRepository.findByNameStartingWith(name);

        int maxSuffix = 0;

        // 동일한 이름을 가진 기존 환자들에 대해 (1), (2), (3) 등의 방법으로 이름 생성
        // 기존 환자들의 이름을 순회하며 사용된 최대 접미사를 계산합니다.
        for (PatientRegistrationEntity existingPatient : existingPatients) {
            String existingName = existingPatient.getName();

            // 이름이 정확히 일치하는 경우, "(1)"로 처리합니다.
            if (existingName.equals(name)) {
                maxSuffix = 1; // 기존 이름이 동일한 경우 접미사 1로 설정
                break; // 더 이상 검사를 진행할 필요 없으므로 루프를 종료합니다.
            } else {
                // 숫자 접미사를 추출하여 최대 접미사를 계산합니다.
                if (existingName.startsWith(name + "(") && existingName.endsWith(")")) {
                    try {
                        int suffix = Integer.parseInt(existingName.substring(name.length() + 1, existingName.length() - 1));
                        maxSuffix = Math.max(maxSuffix, suffix);
                    } catch (NumberFormatException e) {
                        // 숫자 변환에 실패하면 패턴에 맞지 않는다고 간주하고 무시합니다.
                    }
                }
            }
        }

        // 새로 등록할 환자의 이름은 최대 접미사에 1을 더하여 생성합니다.
        int newSuffix = maxSuffix + 1;
        return (newSuffix == 1) ? name : name + "(" + newSuffix + ")";
    }
    

    @Transactional
    public void registerPatientVisit(PatientVisitDTO patientVisitDTO) {
        PatientVisitEntity patientVisit = new PatientVisitEntity();

        // 환자 정보를 조회 및 설정
        PatientRegistrationEntity patient = patientRepository.findByNameAndSecurityNum(patientVisitDTO.getPatientName(), patientVisitDTO.getSecurityNum());
        patientVisit.setPatient(patient);

        // 의사 정보를 조회 및 설정
        DoctorEntity doctor = doctorRepository.findByNo(patientVisitDTO.getDoctorNo()).orElse(null);
        patientVisit.setDoctor(doctor);

        // 간호사 정보를 조회 및 설정
        NurseEntity nurse = nurseRepository.findByNo(patientVisitDTO.getNurseNo()).orElse(null);
        patientVisit.setNurse(nurse);

        patientVisit.setVisitReason(patientVisitDTO.getVisitReason());

        // 환자 방문 정보 저장
        patientVisitRepository.save(patientVisit);
    }
    
}