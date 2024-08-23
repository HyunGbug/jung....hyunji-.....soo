//package com.emr.www.config.jwt;
//
//import java.io.IOException;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.emr.www.service.employee.EmployeeService;
//import com.emr.www.util.jwt.JwtTokenUtil;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter { //jwt 인증 담당
//
//	private final JwtTokenUtil jwtTokenUtil;
//	private final EmployeeService employeeService;
//
//	public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, EmployeeService employeeService) {
//		this.jwtTokenUtil = jwtTokenUtil;
//		this.employeeService = employeeService;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String path = request.getServletPath();
//		System.out.println("JwtAuthenticationFilter activated for path: " + path);
//
//		//jwt 토큰을 요청에서 추출
//		String jwt = jwtTokenUtil.resolveToken(request);
//		System.out.println("토큰 추출 " + jwt);
//
//		//jwt가 유효한 경우에만 인증을 설정
//		if (jwt != null && jwtTokenUtil.validateToken(jwt)) {
//			int userNo = jwtTokenUtil.extractNo(jwt);
//			String role = jwtTokenUtil.extractRole(jwt);
//
//			// 로그로 파싱한 정보 확인
//			System.out.println("Extracted User No: " + userNo);
//			System.out.println("Extracted Role: " + role);
//
//			// 사용자의 주키와 역할을 기반으로 인증 객체 생성
//		    Authentication authentication = employeeService.getAuthenticationFromToken(userNo, role);
//		    System.out.println("JwtAuthentication 클래스에서 인증 객체 생성 및 SecurityContext 설정 여부 : " + authentication);
//
//		    // 여기에서 SecurityContext에 인증 객체를 설정해야 합니다.
//		    if (authentication != null) {
//		        SecurityContextHolder.getContext().setAuthentication(authentication);
//		        System.out.println("SecurityContext에 인증 정보 설정 완료: " + SecurityContextHolder.getContext().getAuthentication());
//		    } else {
//		        System.out.println("인증 객체가 null입니다.");
//		    }
//		}
//
//		// 필터 체인 진행
//		filterChain.doFilter(request, response);
//	}
//
//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		String path = request.getServletPath();
//		System.out.println("Request path: " + path);
//		// 로그인, 회원가입, 주민등록번호 중복 체크 등 인증 없이 접근할 수 있는 경로들
//		return path.equals("/loginMain") || path.equals("/signup") || path.equals("/Login") || path.equals("/registration_form")
//				|| path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/img/") || path.equals("/favicon.ico")
//				|| path.startsWith("/WEB-INF/views/login/");
//	}
//
//}
