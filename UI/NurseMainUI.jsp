<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>전자 의료 기록 시스템</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow-x: hidden;
            font-family: Arial, sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
        }

        header {
            width: 100%;
            background-color: #b8edb5;
            padding: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .header {
            background-color: #f0f0f0;
            padding: 10px;
            margin-bottom: 20px;
        }

        .logo img {
            left: 10px;
            position: absolute;
            width: 250px;
            /* Adjust width as needed */
            height: auto;
            /* Maintain aspect ratio */
        }

        nav {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            flex: 1;
        }

        .nav-btn {
            margin: 0 5px;
            padding: 5px 10px;
            background-color: white;
            border: 1px solid transparent;
            border-radius: 8px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s, border 0.3s;
        }

        .nav-btn:hover {
            border: 1px solid #ccc;
        }

        .logout-btn {
            margin-bottom: -50px;
            font-size: 12px;
            cursor: pointer;
            background-color: white;
            border: 1px solid transparent;
            border-radius: 3px;
        }

        .logout-btn:hover {
            background-color: #e2deded8;
        }

        .profile-info {
            position: relative;
            /* Make this the positioning context */
            display: flex;
            align-items: center;
            margin-right: 30px;
        }

        .status-indicator {
            position: absolute;
            bottom: 0;
            right: 75px;
            width: 14px;
            height: 14px;
            border-radius: 50%;
            background-color: #808080;
        }

        .profile-info img {
            width: 60px;
            height: 60px;
            margin: 0 15px;
            margin-left: 15px;
            margin-right: 10px;
            border-radius: 50%;
            object-fit: cover;
            cursor: pointer;
        }

        .patient-search {
            margin: 10px 0;
            width: 90%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .dropdown-menu {
            display: none;
            width: 130px;
            position: absolute;
            background-color: white;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 100;
            top: 65px;
            right: 35px;
        }

        .dropdown-menu a {
            display: block;
            padding: 8px 16px;
            text-decoration: none;
            color: black;
            border-bottom: 1px solid #ddd;
        }

        .dropdown-menu a:last-child {
            border-bottom: none;
        }

        .dropdown-menu a:hover {
            background-color: #f4f4f4;
        }

        .color-indicator {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 50%;
            margin-right: 8px;
            /* Space between color indicator and text */
        }

        .container {
            display: flex;
            flex: 1;
            transition: all 0.3s ease;
        }

        .leftSidebar,
        .rightSidebar {
            background-color: #e9e9e9;
            width: 230px;
            padding: 10px;
            box-sizing: border-box;
            overflow-y: auto;
            transition: all 0.3s ease;
        }

        .main {
            flex: 1;
            padding: 20px;
            margin: 0 10px;
            background-color: #f4f4f4;
            overflow-y: auto;
            box-sizing: border-box;
            transition: all 0.3s ease;
        }

        .card {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 10px;
            border-radius: 8px;
            height: 100%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        .table-cell {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f4f4f4;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
        }

        .section {
            margin-bottom: 20px;
        }

        .section h2 {
            margin-bottom: 10px;
            font-size: 18px;
        }

        .tabs {
            display: flex;
        }

        .tab {
            flex: 1;
            text-align: center;
            padding: 10px;
            cursor: pointer;
            background-color: #f0f0f0;
            border-bottom: 2px solid transparent;
            transition: background-color 0.3s, border-bottom 0.3s;
        }

        .tab.active {
            background-color: #ffffff;
            border-bottom: 2px solid #38a169;
        }

        .info {
            display: none;
        }

        .info.active {
            display: block;
        }

        .editable {
            padding: 4px;
            margin-top: 5px;
        }

        .editable input {
            border: none;
            width: 100%;
            background: transparent;
            font-size: 14px;
            cursor: default;
            background-color: transparent;
        }

        td.editing {
            background-color: #f4f4f4;
        }

        td.editing input {
            cursor: text;
        }

        .record:hover {
            cursor: pointer;
        }

        .hidden {
            width: 0;
            padding: 0;
            overflow: hidden;
        }
    </style>
</head>

<body>
    <header>
        <div class="logo">
            <img src="Img/Logo.png">
        </div>
        <nav>
            <button id="messages-btn" class="nav-btn">Message</button>
            <button id="chat-ai-btn" class="nav-btn">CHAT AI</button>
            <div class="profile-info">
                <img id="profile-image" src="doctorProfile.png" alt="Profile Image">
                <div class="status-indicator"></div>
                <button id="logout-btn" class="logout-btn">Log Out</button>
                <div class="dropdown-menu">
                    <a href="#" class="status-link" onclick="setStatus('away', '#808080')">
                        <span class="color-indicator" style="background-color: #808080;"></span>자리 비움
                    </a>
                    <a href="#" class="status-link" onclick="setStatus('available', '#008000')">
                        <span class="color-indicator" style="background-color: #008000;"></span>진료중
                    </a>
                    <a href="#" class="status-link" onclick="setStatus('lunch', '#FFA500')">
                        <span class="color-indicator" style="background-color: #FFA500;"></span>점심시간
                    </a>
                </div>
            </div>
        </nav>
    </header>

    <div class="container">
        <div class="leftSidebar">
            <!-- 환자 유형 선택을 위한 라디오 버튼 -->
            <div class="card">
                <div class="tabs">
                    <div id="tab-all-patients" class="tab active">전체 환자</div>
                    <div id="tab-managed-patients" class="tab">관리 환자</div>
                </div>
                <input class="patient-search" type="text" placeholder="환자 검색">
                <button id="refresh-list"
                    style="width: 100%; padding: 10px; background-color: #38a169; color: white; border: none; border-radius: 4px;">새로고침</button>
                <div id="all-patients-info" class="info active">
                    <h2>전체 환자 목록</h2>
                    <!-- 전체 환자 정보 -->
                </div>
                <div id="managed-patients-info" class="info">
                    <h2>관리 환자 목록</h2>
                    <!-- 관리 환자 정보 -->
                </div>
            </div>
        </div>

        <main class="main">
            <section class="patient-details card">
                <div class="header">
                    <h1>환자 EHR</h1>
                    <p id="patient-basic-info">환자 ID: 12345 | 이름: 홍길동 | 생년월일: 1980-01-01 | 성별: 남성</p>
                    <!-- 수정 버튼 추가 -->
                    <button id="edit-btn" class="nav-btn" style="margin-top: 10px;">수정</button>
                    <button id="save-btn" class="nav-btn" style="margin-top: 10px; display: none;">저장</button>
                </div>
                <div class="section">
                    <h2>기본 정보</h2>
                    <table id="basic-info-table">
                        <tr>
                            <th class="table-cell">혈액형</th>
                            <td class="editable table-cell"><input type="text" value="A+" readonly></td>
                            <th class="table-cell">키</th>
                            <td class="editable table-cell"><input type="text" value="175cm" readonly></td>
                            <th class="table-cell">체중</th>
                            <td class="editable table-cell"><input type="text" value="70kg" readonly></td>
                        </tr>
                        <tr>
                            <th class="table-cell">알레르기</th>
                            <td class="editable table-cell" colspan="3"><input type="text" value="페니실린" readonly></td>
                            <th class="table-cell">흡연 여부</th>
                            <td class="editable table-cell"><input type="text" value="비흡연" readonly></td>
                        </tr>
                    </table>
                </div>
                <div class="section">
                    <h2>진료 기록</h2>
                    <table>
                        <tr>
                            <th class="table-cell">날짜</th>
                            <th class="table-cell">진단</th>
                            <th class="table-cell">처방</th>
                            <th class="table-cell">담당의</th>
                        </tr>
                        <tr class="record" data-details="진료 내용: 감기 | 처방: 해열제, 항생제">
                            <td class="table-cell">2024-07-15</td>
                            <td class="table-cell">감기</td>
                            <td class="table-cell">해열제, 항생제</td>
                            <td class="table-cell">김의사</td>
                        </tr>
                        <tr class="record" data-details="진료 내용: 고혈압 | 처방: 혈압약">
                            <td class="table-cell">2024-06-01</td>
                            <td class="table-cell">고혈압</td>
                            <td class="table-cell">혈압약</td>
                            <td class="table-cell">이의사</td>
                        </tr>
                    </table>
                    <div id="record-details" class="section" style="display: none;">
                        <h3>진료 정보</h3>
                        <table id="details-table">
                            <tr>
                                <th class="table-cell">항목</th>
                                <th class="table-cell">내용</th>
                            </tr>
                            <tr>
                                <td class="table-cell">진료 내용</td>
                                <td id="details-content" class="table-cell"></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </section>
        </main>

        <div class="rightSidebar">
            <!-- 오른쪽 사이드바 내용 -->
            <div class="card">
                <p>메신저 기능</p>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('profile-image').addEventListener('click', function (event) {
            var dropdown = document.querySelector('.dropdown-menu');
            dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
            event.stopPropagation(); // 이벤트 버블링 방지
        });

        // 클릭 이외의 부분을 클릭했을 때 메뉴가 사라지도록 처리
        document.addEventListener('click', function (event) {
            var dropdown = document.querySelector('.dropdown-menu');
            if (!event.target.matches('#profile-image') && !dropdown.contains(event.target)) {
                dropdown.style.display = 'none';
            }
        });

        function setStatus(status, color) {
            document.querySelector('.status-indicator').style.backgroundColor = color;
        }

        document.getElementById('tab-all-patients').addEventListener('click', function () {
            setActiveTab('all-patients-info', 'tab-all-patients');
        });

        document.getElementById('tab-managed-patients').addEventListener('click', function () {
            setActiveTab('managed-patients-info', 'tab-managed-patients');
        });

        function setActiveTab(infoId, tabId) {
            document.querySelectorAll('.info').forEach(function (info) {
                info.classList.remove('active');
            });
            document.getElementById(infoId).classList.add('active');

            document.querySelectorAll('.tab').forEach(function (tab) {
                tab.classList.remove('active');
            });
            document.getElementById(tabId).classList.add('active');
        }

        // 진료 기록 클릭 시 세부 정보 표시
        document.querySelectorAll('.record').forEach(function (record) {
            record.addEventListener('click', function () {
                var detailsContent = record.getAttribute('data-details');
                var detailsSection = document.getElementById('record-details');
                var detailsText = document.getElementById('details-content');

                if (detailsSection.style.display === 'block' && detailsText.innerText === detailsContent) {
                    detailsSection.style.display = 'none';
                    detailsText.innerText = '';
                } else {
                    detailsText.innerText = detailsContent;
                    detailsSection.style.display = 'block';
                }
            });
        });

        // 수정 버튼 클릭 시 입력 가능하도록 변경
        document.getElementById('edit-btn').addEventListener('click', function () {
            document.querySelectorAll('.editable').forEach(function (cell) {
                cell.classList.add('editing');
                cell.querySelector('input').removeAttribute('readonly');
            });
            document.getElementById('edit-btn').style.display = 'none';
            document.getElementById('save-btn').style.display = 'inline-block';
        });

        // 저장 버튼 클릭 시 입력 불가능하도록 변경
        document.getElementById('save-btn').addEventListener('click', function () {
            document.querySelectorAll('.editable').forEach(function (cell) {
                cell.classList.remove('editing');
                cell.querySelector('input').setAttribute('readonly', true);
            });
            document.getElementById('edit-btn').style.display = 'inline-block';
            document.getElementById('save-btn').style.display = 'none';
        });

        // 메세지 (오른쪽 사이드바) 부분 버튼
        document.getElementById('messages-btn').addEventListener('click', function () {
            var rightSidebar = document.querySelector('.rightSidebar');
            var mainContent = document.querySelector('.main');
            if (rightSidebar.classList.contains('hidden')) {
                rightSidebar.classList.remove('hidden');
                mainContent.style.flex = '1';
            } else {
                rightSidebar.classList.add('hidden');
                mainContent.style.flex = '2';
            }
        });
    </script>
</body>

</html>
