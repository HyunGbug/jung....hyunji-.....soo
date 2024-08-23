//package com.emr.www.config.jwt;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.session.HttpSessionEventPublisher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//	}
//
//	@Bean
//	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		System.out.println("필터체인 메서드 활성화");
//
//		// CSRF 보호 비활성화 > jwt 사용
//		http.csrf(csrf -> csrf.disable());
//
//		// 세션 관리 설정: 세션이 필요할 때만 생성, 비활동 시 자동 로그아웃을 위해 사용
//		/*
//		 * http.sessionManagement(session ->
//		 * session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//		 * .expiredUrl("/loginMain?sessionExpired=true"));
//		 */
//
//		// 세션 비활성화 (JWT 기반 인증으로 상태 없는 방식 사용)
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		// 세션 고정 공격 방지
//		//http.sessionManagement(session -> session.sessionFixation().migrateSession());
//
//		// URL 접근 제어 설정
//		http.authorizeHttpRequests(auth -> auth.requestMatchers("/css/**", "/js/**", "/img/**", "/images/**").permitAll() // 정적  리소스에 대한 접근 허용 
//				.requestMatchers("/loginMain", "/signup", "/registration_form", "/Login", "/inactivity-logout", "/logout").permitAll() // 로그인,회원가입 페이지 및 리소스는 누구나 접근 가능
//				.requestMatchers("/WEB-INF/views/login/**").permitAll() // WEB-INF 내부의 로그인 페이지에 대한 모든 접근 허용 
//				.requestMatchers("/WEB-INF/views/admin/**").hasRole("ADMIN")//관리자 jsp에 의사 접근 허용
//				.requestMatchers("/WEB-INF/views/doctor/**").hasRole("DOCTOR") //의사 jsp에 의사 접근 허용
//				.requestMatchers("/WEB-INF/views/headNurse/**").hasRole("H")//수간호사 jsp에 의사 접근 허용
//				.requestMatchers("/WEB-INF/views/nurse/**").hasRole("N")//간호사 jsp에 의사 접근 허용
//				.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 허용
//				.requestMatchers("/doctor/**").hasRole("DOCTOR") // 의사만 접근 허용
//				.requestMatchers("/api/doctor/**").hasRole("DOCTOR") // 의사만 API 접근 가능
//				.requestMatchers("/nurse/**").hasRole("N") // 간호사만 접근 허용
//				.requestMatchers("/headNurse/**").hasRole("H") // 수간호사만 접근 허용
//				.requestMatchers("/api/patients/**").hasRole("H") // 수간호사만 접근 허용
//				.anyRequest().denyAll() // 이외의 모든 요청은 거부
//		//.anyRequest().authenticated() // 나머지 요청도 인증된 사용자만 접근 가능하게 만듭니다. ex) 간호사 역할의 토큰을 가지고있는 사람이 의사 페이지로 이동 가능
//		);
//
//		// JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		// 로그아웃 설정
//		http.logout(logout -> logout
//	            .logoutUrl("/logout")  // 로그아웃 URL 설정
//	            .invalidateHttpSession(true)  // 세션 무효화
//	            .deleteCookies("JSESSIONID", "jwtToken")  // 쿠키 삭제
//	            .logoutSuccessUrl("/loginMain"));  // 로그아웃 후 리디렉트
//
//		return http.build();
//
//	}
//	@Bean
//	protected HttpSessionEventPublisher httpSessionEventPublisher() {
//		return new HttpSessionEventPublisher();
//	}
//}
