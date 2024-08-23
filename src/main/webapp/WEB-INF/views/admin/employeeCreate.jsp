<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원장 - 직원 추가</title>

<style>
.form-container {
	width: 100%;
	max-width: 600px; /* 최대 너비를 설정하여 너무 넓지 않도록 제한 */
	padding: 20px;
	background-color: #e2e5e291;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	box-sizing: border-box;
	margin: 0 auto;
}

form {
	margin: 0;
	font-size: 16px;
}

label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
}

.emplWrite {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
	font-size: 16px;
	box-sizing: border-box;
}

.position {
	margin-right: 6px;
}

.radio-group label {
	margin-right: 20px;
	font-weight: normal;
}

.radio-group {
	margin-bottom: 20px;
}

.emplAdd {
	width: 100%;
	padding: 12px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 25px;
	transition: background-color 0.3s;
}

.emplAdd:hover {
	background-color: #45a049;
}

.form-container h1 {
	margin-bottom: 20px;
	font-size: 24px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="form-container">
		<h1>직원 추가</h1>
		<form id="employeeForm" method="post" enctype="multipart/form-data">
			<div class="radio-group">
				<label><input type="radio" name="job" value="doctor"
					class="job" required> 의사</label> <label><input type="radio"
					name="job" value="nurse" class="job" required> 간호사</label>
			</div>

			<div class="form-group doctor-field">
				<label for="position">직급:</label> <select id="position-doctor"
					name="position" class="emplWrite">
					<option value="인턴">인턴</option>
					<option value="레지던트">레지던트</option>
					<option value="전문의">전문의</option>
					<option value="교수">교수</option>
					<option value="퇴직">퇴직</option>
				</select>
			</div>

			<div class="form-group nurse-field" style="display: none;">
				<label for="position">직급:</label> <select id="position-nurse"
					name="position" class="emplWrite">
					<option value="N">간호사</option>
					<option value="H">수간호사</option>
					<option value="퇴직">퇴직</option>
				</select>
			</div>

			<label for="name">이름:</label> <input type="text" id="name"
				name="name" placeholder="이름" class="emplWrite" required>

			<div class="form-group">
				<label for="phone">전화번호:</label> <input type="text" id="phone"
					name="phone" placeholder="010-1234-5678" class="emplWrite"
					maxlength="13" required>
			</div>

			<label for="ssn">주민번호:</label> <input type="text" id="birthdate"
				name="securityNum" class="emplWrite" placeholder="000000-0000000"
				maxlength="14" required> <label for="email">이메일:</label> <input
				type="email" id="email" name="email" class="emplWrite"
				placeholder="이메일" required>

			<div class="form-group">
				<label for=departmentId>전문분야:</label> <input type="text"
					id="departmentId" name="departmentId" class="emplWrite">
			</div>

			<label for="photo">증명사진:</label> <input type="file" id="photo"
				name="photo" accept="image/*" class="emplWrite" required>

			<button type="submit" class="emplAdd"
				onclick="handleFormSubmit()">직원 추가</button>
		</form>
	</div>
</body>
<script>
<!-- employeeCreate.jsp 스크립트 시작 -->    
function validateAndSubmit(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음

    // 입력값 가져오기
    const ssn = document.getElementById("birthdate").value; // 주민번호 입력값
    const phone = document.getElementById("phone").value; // 전화번호 입력값
    const email = document.getElementById("email").value; // 이메일 입력값
    const name = document.getElementById("name").value; // 이름 입력값
    const department = document.getElementById("department").value; // 부서 입력값

    // 유효성 검사
    if (!validateName(name)) {
        alert("이름을 입력해 주세요.");
        return;
    }

    if (!validateEmail(email)) {
        alert("이메일 형식이 잘못되었습니다. 올바른 형식: example@example.com");
        return;
    }

    if (!validateSSN(ssn)) {
        alert("주민번호 형식이 잘못되었습니다. 올바른 형식: 123456-1234567");
        return;
    }

    if (!validatePhone(phone)) {
        alert("전화번호 형식이 잘못되었습니다. 올바른 형식: 010-1234-5678");
        return;
    }

    if (!validateDepartment(department)) {
        alert("부서를 선택해 주세요.");
        return;
    }

    // 주민번호 중복 확인 후 폼 제출
    checkSSNInDatabase(ssn, function(isValid) {
        if (!isValid) {
            alert("이미 존재하는 주민번호입니다.");
            return;
        }
        // 유효성 검사를 모두 통과한 경우에만 폼 제출
        submitForm();
    });
}

// 유효성 검사 함수들
function validateName(name) {
    return name.trim() !== ""; // 이름이 비어 있지 않은지 확인
}

function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return emailPattern.test(email); // 이메일 형식 확인
}

function validateSSN(ssn) {
    const ssnPattern = /^\d{6}-\d{7}$/; // 주민번호 형식 패턴
    return ssnPattern.test(ssn); // 패턴과 일치하는지 확인
}

function validatePhone(phone) {
    const phonePattern = /^\d{3}-\d{4}-\d{4}$/; // 전화번호 형식 패턴
    return phonePattern.test(phone); // 패턴과 일치하는지 확인
}

function validateDepartment(department) {
    return department !== ""; // 부서가 선택되었는지 확인
}

// 주민번호 중복 확인 (서버와 통신)
function checkSSNInDatabase(ssn, callback) {
    fetch('/admin/checkDuplicateSSN', { // Spring Boot에서 이 경로로 매핑
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ ssn: ssn })
    })
    .then(response => response.json())
    .then(data => {
        callback(!data.duplicate); // 중복이면 false 반환
    })
    .catch(error => {
        console.error('Error checking SSN:', error);
        callback(false);
    });
}

// 폼 제출 처리
function submitForm() {
    const form = document.querySelector("form");
    const job = document.querySelector('input[name="job"]:checked');
    
    if (!job) {
        alert('직업을 선택해 주세요.');
        return;
    }

    const actionUrl = job.value == "doctor" ? "/admin/doctorCreate" : "/admin/nurseCreate";

    const formData = new FormData(form);
    fetch(actionUrl, {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('네트워크 응답이 정상이 아닙니다.');
        }
        return response.text();
    })
    .then(data => {
        alert(data); 
    })
    .catch(error => {
        console.error('Error:', error);
        alert('직원 추가 중 오류가 발생했습니다.');
    });
}

// 주민등록번호 입력 시 자동으로 "-" 추가
document.getElementById('birthdate').addEventListener('input', function() {
    let value = this.value.replace(/[^0-9]/g, ''); // 입력값에서 숫자만 남김

    if (value.length > 6) {
        // 앞 6자리와 뒤 7자리로 나눠서 자동으로 "-" 추가
        this.value = value.slice(0, 6) + '-' + value.slice(6, 13);
    } else {
        // 6자리 이하일 경우 "-" 없이 그대로 표시
        this.value = value;
    }
});


// 주민등록번호 입력 후 중복 여부 확인
document.getElementById('birthdate').addEventListener('blur', function() {
    const ssn = this.value;
    if (ssn.length == 14) { // "-" 포함한 전체 길이 확인
        checkDuplicateSSN(ssn);
    }
});

// 주민번호 중복 확인 함수 (서버 통신)
function checkDuplicateSSN(ssn) {
    fetch('/admin/checkDuplicateSSN', { // Spring Boot에서 이 경로로 매핑
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ ssn: ssn })
    })
    .then(response => response.json())
    .then(data => {
        if (data.duplicate) {
            alert('이미 존재하는 주민번호입니다.');
            document.getElementById('birthdate').value = ''; // 중복 시 입력값 초기화
        }
    })
    .catch(error => {
        console.error('Error checking SSN:', error);
        alert('주민번호 중복 확인 중 오류가 발생했습니다.');
    });
}


// 새로 로드된 콘텐츠의 폼 스크립트 초기화
function initializeFormScripts() {
    console.log("Form element:", document.querySelector("form"));
    console.log("Job input elements:", document.querySelectorAll('input[name="job"]'));

    function updateJobTitleOptions() {
        const jobElement = document.querySelector('input[name="job"]:checked');

        if (jobElement) {
            const job = jobElement.value;
            console.log("Selected Job:", job);

            if (job == "doctor") {
                document.querySelectorAll('.doctor-field').forEach(e => e.style.display = 'block');
                document.querySelectorAll('.nurse-field').forEach(e => e.style.display = 'none');
                document.getElementById('position-doctor').disabled = false;
                document.getElementById('position-doctor').style.display = 'block';
                // 간호사 직급 필드 비활성화 및 숨기기
                document.getElementById('position-nurse').disabled = true;
                document.getElementById('position-nurse').style.display = 'none';
                
            } else if (job == "nurse") {
                document.querySelectorAll('.doctor-field').forEach(e => e.style.display = 'none');
                document.querySelectorAll('.nurse-field').forEach(e => e.style.display = 'block');
                // 간호사 직급 필드 활성화
                document.getElementById('position-nurse').disabled = false;
                document.getElementById('position-nurse').style.display = 'block';

                // 의사 직급 필드 비활성화 및 숨기기
                document.getElementById('position-doctor').disabled = true;
                document.getElementById('position-doctor').style.display = 'none';
            }
        } else {
            console.error("No job selected or radio buttons not loaded properly.");
        }
    }

    // 직업 선택 시 옵션 업데이트
    const jobInputs = document.querySelectorAll('input[name="job"]');
    jobInputs.forEach((elem) => {
        elem.removeEventListener('change', updateJobTitleOptions);
        elem.addEventListener('change', updateJobTitleOptions);
    });

    updateJobTitleOptions();

    // 폼 제출 이벤트 처리
    const form = document.querySelector("form");
    if (form) {
        form.removeEventListener('submit', handleFormSubmit);
        form.addEventListener("submit", handleFormSubmit);
    }
}

// 폼 제출 처리 함수
function handleFormSubmit(event) {
    event.preventDefault();
    const job = document.querySelector('input[name="job"]:checked');
    if (!job) {
        alert('직업을 선택해 주세요.');
        return;
    }
    const actionUrl = job.value == "doctor" ? "/admin/doctorCreate" : "/admin/nurseCreate";

    const formData = new FormData(event.target);
    fetch(actionUrl, {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(data => {
        alert(data); 
    })
    .catch(error => {
        console.error('Error:', error);
        alert('직원 추가 중 오류가 발생했습니다.');
    });
}

// 페이지가 처음 로드될 때 스크립트를 초기화합니다.
document.addEventListener('DOMContentLoaded', function() {
    initializeFormScripts();
});

</script>

</html>
