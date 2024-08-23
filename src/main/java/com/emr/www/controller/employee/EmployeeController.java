//package com.emr.www.controller.employee;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.emr.www.entity.doctor.DoctorEntity;
//import com.emr.www.entity.nurse.NurseEntity;
//import com.emr.www.repository.doctor.DoctorRepository;
//import com.emr.www.repository.nurse.NurseRepository;
//import com.emr.www.service.employee.EmployeeService;
//import com.emr.www.util.jwt.JwtTokenUtil;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Controller
//public class EmployeeController {
//
//	@Autowired
//	public EmployeeService employeeService;
//	
//	@Autowired
//	public DoctorRepository doctorRepository;
//
//	@Autowired
//	public NurseRepository nurseRepository;
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil; // JWT 토큰 유틸리티 클래스 주입
//
//	//로그인 메인 페이지
//	@GetMapping("/loginMain")
//	public String showLoginPage(@RequestParam(required = false) String sessionExpired, Model model, HttpServletResponse response) {
//		return "login/loginMain"; // 로그인 페이지로 이동
//	}
//
//	//로그인 회원 가입 페이지
//	@GetMapping("/registration_form")
//	public String showRegistrationForm() {
//		return "login/registration_form"; // src/main/webapp/WEB-INF/views/login/registration_form.jsp 로 렌더링
//	}
//
//	// 회원가입 처리
//	@PostMapping("/signup")
//	public String handleSignup(@RequestParam String licenseId, @RequestParam String password, RedirectAttributes redirectAttributes) {
//		String result = employeeService.registerUser(licenseId, password);
//		redirectAttributes.addFlashAttribute("message", result);
//		return "redirect:/registration_form"; // 회원가입 창으로 돌아감 (등록 완료 후 닫힘 처리)
//	}
//
//	// 로그인 처리
//	@PostMapping("/Login")
//	public String login(@RequestParam String licenseId, @RequestParam String password, @RequestParam(required = false) boolean isAdmin,
//			RedirectAttributes redirectAttributes, HttpServletResponse response) {
//
//		try {
//			// 데이터베이스 사용자 인증 및 토큰 생성
//			String token = employeeService.authenticateAndGenerateToken(licenseId, password, isAdmin);
//
//			System.out.println("Employee Controller에서 토큰 생성 받음 : " + token);
//			// JWT 토큰을 쿠키에 저장 (12시간 유지)
//			Cookie jwtCookie = new Cookie("jwtToken", token);
//			jwtCookie.setHttpOnly(false); // 보안 설정
//			jwtCookie.setPath("/");
//			jwtCookie.setMaxAge(12 * 60 * 60); // 12시간 유지
//			response.addCookie(jwtCookie);
//
//			// 토큰에서 역할을 추출하여 해당 페이지로 리디렉션
//			String role = jwtTokenUtil.extractRole(token);
//			return getRedirectPathByRole(role);
//
//		} catch (IllegalArgumentException e) {
//			// 예외 발생 시 로그인 페이지로 리다이렉트하고 에러 메시지를 추가
//			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//			return "redirect:/loginMain";
//		} catch (Exception e) {
//			// 예기치 못한 역할에 대한 처리
//			redirectAttributes.addFlashAttribute("errorMessage", "해당 계정은 등록되어 있지 않습니다. 병원장에게 문의해주세요.");
//			return "redirect:/loginMain";
//		}
//	}
//
//	// 역할에 따라 리디렉션할 경로 반환
//	private String getRedirectPathByRole(String role) {
//		switch (role) {
//			case "ADMIN" :
//				return "redirect:/admin/main"; //관리자 메인 페이지
//
//			case "DOCTOR" :
//				return "redirect:/doctor/main"; //의사 메인 페이지
//
//			case "H" :
//				return "redirect:/headNurse/main"; //수간호사 메인 페이지
//
//			case "N" :
//				return "redirect:/nurse/main"; //간호사 메인 페이지
//
//			default :
//				return "redirect:/loginMain"; // 유효하지 않은 역할일 경우 로그인 페이지로 리디렉트
//		}
//	}
//
//	//비활동 30분 로그아웃 - jwt 토큰을 쿠키에서 삭제하면서 진행
//	@PostMapping("/inactivity-logout")
//	public void inactivityLogout(HttpServletResponse response) {
//		// JWT 쿠키 삭제
//		Cookie jwtCookie = new Cookie("jwtToken", null);
//		jwtCookie.setMaxAge(0);
//		jwtCookie.setPath("/");
//		response.addCookie(jwtCookie);
//
//		// 서버는 쿠키만 삭제하고, 클라이언트가 로그인 페이지로 리디렉트하도록 처리
//		response.setStatus(HttpServletResponse.SC_OK);
//	}
//	
//	
//	/* -------------------------------------------------------관리자 --------------------------------------------------------- */
//	
//	// 직원 검색 메서드
//	@GetMapping("/searchEmployees")
//    @ResponseBody
//    public List<Map<String, Object>> searchEmployees(@RequestParam(required = false) String name,
//                                        @RequestParam(required = false) String job,
//                                        @RequestParam(required = false) String position) {
//        // 검색 조건에 따라 직원 리스트를 반환합니다.
//		List<Object> employees = employeeService.searchEmployees(name, job, position);
//        
//		return employees.stream().map(employee -> {
//            Map<String, Object> map = new HashMap<>();
//            if (employee instanceof DoctorEntity) {
//                DoctorEntity doctor = (DoctorEntity) employee;
//                map.put("no", doctor.getNo());
//                map.put("name", doctor.getName());
//                map.put("position", doctor.getPosition());
//                map.put("job", "doctor");
//                map.put("securityNum", doctor.getSecurityNum());
//                map.put("email", doctor.getEmail());
//                map.put("phone", doctor.getPhone());
//                map.put("licenseId", doctor.getLicenseId());
//                map.put("password", doctor.getPassword());
//                map.put("departmentId", doctor.getDepartmentId());
//                map.put("activeStatus", doctor.getActiveStatus());
//            } else if (employee instanceof NurseEntity) {
//                NurseEntity nurse = (NurseEntity) employee;
//                map.put("no", nurse.getNo());
//                map.put("name", nurse.getName());
//                map.put("position", nurse.getPosition());
//                map.put("job", "nurse");
//                map.put("securityNum", nurse.getSecurityNum());
//                map.put("email", nurse.getEmail());
//                map.put("phone", nurse.getPhone());
//                map.put("licenseId", nurse.getLicenseId());
//                map.put("password", nurse.getPassword());
//                map.put("departmentId", nurse.getDepartmentId());
//                map.put("activeStatus", nurse.getActiveStatus());
//            }
//            return map;
//        }).collect(Collectors.toList());
//    }
//
//	// 모든 직원 목록 가져오기
//	@GetMapping("/getEmployees")
//	@ResponseBody
//	public List<Object> getEmployees() {
//		// 전체 직원 목록을 반환합니다.
//		return employeeService.searchEmployees(null, null, null);
//	}
//
//	// 직원 정보 수정 메서드
//	@PostMapping("/updateEmployee")
//	@ResponseBody
//	public ResponseEntity<String> updateEmployee(@RequestParam int no, @RequestParam String name,
//			@RequestParam String position, @RequestParam String phone, @RequestParam String email,
//			@RequestParam String password, @RequestParam(required = false) String department,
//			@RequestParam(required = false) String job) {
//		try {
//			// 직원 정보를 업데이트합니다. job 파라미터로 직업군을 전달합니다.
//			employeeService.updateEmployee(no, name, position, phone, email, department, password, job);
//			return ResponseEntity.ok("수정이 완료되었습니다.");
//		} catch (Exception e) {
//			// 업데이트 중 오류 발생 시 오류 메시지 반환
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생했습니다.");
//		}
//	}
//
//	@PostMapping("/checkDuplicateSSN")
//	public ResponseEntity<Map<String, Boolean>> checkDuplicateSSN(@RequestBody Map<String, String> request) {
//		String ssn = request.get("ssn");
//
//		// EmployeeService를 통해 중복 체크 수행
//		try {
//			employeeService.checkSecurityNumDuplicate(ssn);
//			// 중복이 없으면 false 반환
//			Map<String, Boolean> response = new HashMap<>();
//			response.put("duplicate", false);
//			return ResponseEntity.ok(response);
//		} catch (IllegalArgumentException e) {
//			// 중복이 있을 경우 true 반환
//			Map<String, Boolean> response = new HashMap<>();
//			response.put("duplicate", true);
//			return ResponseEntity.ok(response);
//		}
//	}
//}
