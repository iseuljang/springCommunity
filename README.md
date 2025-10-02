:speech_balloon: 커뮤니티
-
기업 내 근무 관리와 커뮤니케이션을 효율적으로 지원하는 사내 시스템 개발을 목표로 합니다.

<br>

🔗 완성된 웹 애플리케이션 보기
-

<!--
⚠️ 기존에는 AWS EC2와 RDS를 이용해 배포했으나, 무료 사용 기간 종료로 요금 부담이 예상되어 서비스를 중단했습니다.<br>
현재는 Render와 Clever Cloud를 이용해 배포했으며, 아래 캡처 화면을 통해 프로젝트의 주요 기능과 UI를 확인하실 수 있습니다.<br><br>
-->


<a href="https://springcommunity.onrender.com/" target="_blank">https://springcommunity.onrender.com </a>

관리자 아이디 : SYSTEM <br>
관리자 비밀번호 : 1234

<br>


🔗 PPT
-
[커뮤니티최종발표.pptx](https://github.com/user-attachments/files/18312758/default.pptx)


<br>

## 목차
  - [개발기간](#개발기간)
  - [팀 구성](#팀-구성)
  - [개발환경](#개발환경)
  - [담당한 기능](#담당한-기능)
  - [트러블 슈팅](#트러블-슈팅)
  - [개선할 부분](#개선할-부분)


<br>


🗓️개발기간 
-
  + 2024.11.28 ~ 2025.01.03

<br>

👥팀 구성
-
  + 팀장 송지은
    + 기획 : HTML, CSS 제작, 프로토타입, 화면설계서, 사내규정 제정
    + 개발 : 비밀번호 재설정, 내정보조회 및 수정, 근태 관리, 복지 관리
  + 팀원 장이슬
    + 기획 : HTML, CSS 제작, 시퀀스(로그인), 프로젝트 주제제안서, 일정관리(노션), ERD
    + 개발 : 로그인&로그아웃(스프링시큐리티), 채팅방 기능(웹소켓), 관리자 기능, 게시판CRUD, 댓글CRUD, 스프링 필터(XSS 및 비속어처리), 스프링 스케쥴러(회원등록 후 아이디 및 초기비밀번호 이메일 발송)
  + 팀원 박인재
    + 기획 : HTML, CSS 제작, 요구사항분석서, 프로젝트설계서 작성, PPT
    + 개발 : 부서업무 관리, 주간근무시간 관리, 출퇴근 관리(지오로케이션) 
  

<br>

🛠개발환경
-
  + JDK 1.8, MySQL 8.0, TOMCAT 9.0, SPRING FRAMEWORK 4.3.3.RELEASE, SPRING SECURITY 3.2.10.RELEASE, MyBatis 3.4.1
  + JAVA8, HTML5, CSS3, JSP4, JavaScript, jQuery, Ajax
  + SpringToolSuite 4, Visual Studio Code, ERMaster, StarUML, MySQL (Workbench 8.0),GitHub, Notion

<br>


<br>

🖥담당한 기능
-
  - **ERD**  
  ![ERD](https://github.com/iseuljang/springCommunity/blob/main/document/SQL/ERD(%EC%B5%9C%EC%A2%85).jpg)

<!--
  ![image](https://github.com/user-attachments/assets/e7c40f24-6cf8-4dc1-8868-44950389065e)
-->

  - **시퀀스(로그인)**
  ![시퀀스(로그인)](https://github.com/user-attachments/assets/3b47dbd4-28b3-4e22-b30d-8e7e44320e78)


  - **일정관리(노션)**
    - 작업현황
    ![image](https://github.com/user-attachments/assets/cb00a42a-6cd3-49d4-8607-116bf3c8ec90)
    ![image](https://github.com/user-attachments/assets/b21e01fa-fb0e-4395-8b26-3d91c26f1dbb)
    - 타임라인
    ![image](https://github.com/user-attachments/assets/5d703995-dbbf-4c02-8db3-433199df3258)
    ![image](https://github.com/user-attachments/assets/01af9c90-65a6-4356-8a84-563c0a1770fb)


  - **로그인&로그아웃**
    - 스프링 시큐리티를 사용하여 로그인처리, 로그아웃처리
    - https://github.com/iseuljang/springCommunity/blob/main/src/main/webapp/WEB-INF/spring/security/security-context.xml   
    - https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service
      - UserLoginSuccessHandler.java
      - UserDeniedHandler.java
      - UserAuthenticationService.java
    ![login](https://github.com/iseuljang/springCommunity/blob/main/screen/main(not%20login).jpg)
    ![loginModal](https://github.com/iseuljang/springCommunity/blob/main/screen/login.jpg)
    - 로그인 실패시 모달창에서 알림
      -  https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service/UserLoginFailureHandler.java
    ![loginFail](https://github.com/iseuljang/springCommunity/blob/main/screen/login%20fail.jpg)

  - **관리자 기능**
    - 직원등록
      - 단건인 경우 직접 입력하여 직원을 등록
      - 다건인 경우 지정된 엑셀 양식을 받아 입력하여 업로드시켜 직원등록
      - 직원이 직접 회원가입하는 기능이 없으며 아이디의 경우 "jj"에 사번을 결합하여 아이디를 생성하고 초기비밀번호는 알파벳소문자와 숫자의 랜덤결합으로 6자리 생성
      - 등록된 직원의 이메일로 직원 아이디와 초기비밀번호를 발송(<strong>스케쥴러</strong>)
      
    - 직원목록 조회 및 정보수정
      - 등록되어있는 직원 목록을 조회
      - 조회 조건은 직원 이름, 재직상태, 부서, 직책, 입사일
      - 직원의 재직상태와 부서, 직책을 수정할 수 있음
    - 소스코드
      - 관리자컨트롤러 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/controller/AdminController.java
      - 관리자 DAO : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/dao/AdminDAO.java
      - 관리자 서비스 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service
        - AdminService.java
        - AdminServiceImpl.java
      - 엑셀 파일 읽기 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service/ExcelUserService.java
      - 이메일 스케쥴러 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/scheduler/EmailScheduler.java
      - 네이버메일 발송서비스 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service/NaverEmailService.java
      - 이메일설정 : https://github.com/iseuljang/springCommunity/blob/main/src/main/webapp/WEB-INF/spring/email-context.xml
    <img src="https://github.com/iseuljang/springCommunity/blob/main/screen/%EA%B4%80%EB%A6%AC%EC%9E%90.gif">
    
  - **채팅방 기능**
    - 채팅방 생성
      - 채팅방 생성 버튼 클릭시 이름, 부서, 직책으로 검색할 수 있는 모달창이 뜸
      - 채팅방 생성 완료시 좀전에 생성한 채팅방이 모달창으로 뜸
    - 채팅방 조회
      - 드래그로 채팅 모달 이동 가능
      - 햄버거 메뉴를 클릭하여 채팅참여자 목록 확인가능
      - 채팅초대 클릭시 직원 검색 모달이 뜨며, 해당 채팅방에 참여하지 않은 직원들만 조회
      - 초대시 채팅방 안에서 초대됨을 알리는 메시지가 출력
      - 채팅 내용입력시 그날의 날짜가 없는 경우 날짜 표시. 해당 날짜에 메시지가 있는 경우 메시지만 추가됨
      - 해당 메시지를 읽지 않은 사람의 수 표시
      - 여러 채팅방을 열어놓은 채로 번갈아 가며 채팅입력 가능
      - 필터를 사용하여 XSS 방지 및 비속어 처리(웹소켓을 사용하여 입력하기 때문에 처음 입력시는 반영되지만 이후 필터동작하여 처리됨)(<strong>필터</strong>)
    - 채팅방 상단고정
      - 즐겨찾는 채팅방등을 상단고정 처리하여 항상 맨위에 조회되게 할 수 있음
    - 채팅방 목록
      - 채팅 조회시 해당 채팅방에서 마지막으로 입력된 메시지와 시간이 표시
      - 안읽은 메시지가 있는 경우 안읽은 메시지 갯수 표시
      - 인터벌을 사용하여 최근 채팅 입력된 채팅방이 목록의 상단으로 올라옴
      - 채팅 참가인원이 3명 이상일 경우 참가자 수 표시
      - 채팅방 이름으로 채팅 검색
    - 메인
      - 전체 채팅방 안에서 읽지 않은 총 메시지 갯수가 메인에 표시
      - 안읽은 메시지 갯수가 9를 넘어가면 9+, 99를 넘어가면 99+로 표시
    - 채팅방 이름
      - 채팅방 이름은 1대1 채팅의 경우 참가한 서로의 이름으로 표시
      - 단체채팅방의 경우 초대된 사람의 이름을 ,로 구분하여 참가자 이름들이 채팅방 이름이 됨
      - 이후 채팅방 안에서 채팅방 이름을 클릭하여 채팅방 이름 수정 가능
      - 채팅방 이름은 참가자 모두 본인이 원하는 각자의 이름으로 저장 가능
    - 채팅방 나가기
      - 채팅방에서 나가기를 누를 경우 나감 상태로 변경되며 채팅방 안에서는 나갔음을 메시지로 표시
      - 채팅방 나감 상태인 직원은 채팅목록에서 제외되며 다시 초대 가능
      - 해당 채팅방의 참가자 전원이 나감 상태인 경우 채팅방 비활성화처리
    - 소스코드
      - html, 스크립트 소스 : https://github.com/iseuljang/springCommunity/blob/main/src/main/webapp/WEB-INF/views/include/header.jsp
      - 채팅 서비스 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/service
        - ChatService.java, ChatServiceImpl.java
        - 웹소켓 : ChatWebSocketHandler.java, WebSocketConfig.java
      - 채팅 DAO : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/dao/ChatDAO.java
      - 채팅 컨트롤러 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/controller/ChatController.java
      - 필터 : https://github.com/iseuljang/springCommunity/blob/main/src/main/java/com/springCommunity/filter/ContentFilter.java
    <img src="https://github.com/iseuljang/springCommunity/blob/main/screen/%EC%B1%84%ED%8C%85.gif">
    
  - **게시판 CRUD**
    - 게시글 등록 및 수정시 필터를 사용하여 XSS 방지 및 비속어 처리
    ![image](https://github.com/user-attachments/assets/4880fe3d-f112-4e0b-9d9d-2066091b498e)
    ![image](https://github.com/user-attachments/assets/95314afb-1a0d-4fda-a17e-2cbb2ac905bf)
    ![image](https://github.com/user-attachments/assets/16335073-0d59-4dba-afaf-f4ddadcf3efd)
    ![image](https://github.com/user-attachments/assets/d80af95c-1a20-4b75-a348-e3967a4916df)
    ![image](https://github.com/user-attachments/assets/6f5d61bc-230c-4794-a00a-ea26cbeab988)


  - **댓글 CRUD**
    - 댓글 등록 및 수정시 필터를 사용하여 XSS 방지 및 비속어 처리
    ![image](https://github.com/user-attachments/assets/03ff6aa3-6324-425b-b16e-7a8f85f69249)
    ![image](https://github.com/user-attachments/assets/c80cdab9-9d7e-4789-881b-add82133dd73)
    ![image](https://github.com/user-attachments/assets/c59b38f4-720c-41c3-ac20-c40028528ddc)
    ![image](https://github.com/user-attachments/assets/d7364038-be98-4e78-b733-0575ca7ad2c4)

  
<br>

💡트러블 슈팅
-
1️⃣ 채팅방 안에서 직원 검색 오류 
  - 문제 배경
    - 채팅방 안에서 직원 검색시 이미 참가하고 있는 유저가 뜨거나, 참가하지 않은 직원인데도 뜨지 않는 오류 발생
    - 여러 채팅방이 열리고 있기 때문에 채팅방 기본키가 엉켜서 발생한 오류
  - 해결 방법
    - 참가자 초대 모달을 하나만 두고 여는게 아니라 각 채팅방에 맞춰 생성되도록 수정
  - 코드 비교
    - 
      - 수정전
      ```
      function chatUserAdd(chat_no,chat_users_name) {
          // 모달 창 열기
          $("#addUserModal").fadeIn();
      ```
      - 수정후
      ```
      function chatUserAdd(chat_no, chat_users_name) {
          const modalId = "addUserModal_"+chat_no; // 고유한 모달 ID 생성
      
          // 이미 모달이 존재하면 열기만 함
          if($("#"+modalId).length > 0) {
              $("#"+modalId).fadeIn();
              return;
          }
      
          // 모달 HTML 동적 생성
          const modalHTML = `
              <div id="\${modalId}" class="addUserModalWrapper">
                  <div class="addUserModal">
                      <div class="modalHeader">
                          <h2>참가자 초대</h2>
                          <button class="closeBtn" onclick="closeAddUserModal('\${modalId}')">X</button>
                      </div>
                      <input type="text" id="user_search_add_\${chat_no}" class="user_search_add" placeholder="이름, 부서, 직책으로 검색하세요">
                      <ul id="addUserList_\${chat_no}" class="addUserList" style="display:none;"></ul>
                      <button id="addUserButton_\${chat_no}" class="addUserButton">초대</button>
                  </div>
              </div>
          `;
      
          // Body에 모달 추가
          $("body").append(modalHTML);
      
          // 모달 스타일링
          $("#"+modalId).fadeIn();
          
          // 외부 클릭으로 모달 닫기
          $("#"+modalId).on("click", function (event) {
              if($(event.target).hasClass("addUserModalWrapper")) {
                  closeAddUserModal(modalId); // 외부 클릭 시 모달 닫기
              }
          });
      
          // 검색 기능 초기화
          $(document).on('keyup', `#user_search_add_\${chat_no}`, function () {
              let search_value = $(this).val();
              if (search_value.length > 0) {
                  $.ajax({
                      url: '<%= request.getContextPath() %>/chat/searchUsers.do',
                      method: 'GET',
                      data: { search_value: search_value, chat_no: chat_no },
                      success: function (data) {
                          $("#addUserList_"+chat_no).empty();
                          if(data.length === 0) {
                              $("#addUserList_"+chat_no).append('<li>검색 결과가 없습니다.</li>');
                          }else {
                              $("#addUserList_"+chat_no).show();
                              data.forEach(function (item) {
                                  $("#addUserList_"+chat_no).append(
                                      `<li>
                                          <input type="checkbox" value="\${item.user_id}:\${item.user_name}">
                                          \${item.user_name} - \${item.department_name} - \${item.job_position_name}
                                      </li>`
                                  );
                              });
                          }
                      },
                      error: function () {
                          alert("검색 중 문제가 발생했습니다.");
                      }
                  });
              }
          });
      
          // 사용자 초대 처리
          $(document).on("click", `#addUserButton_\${chat_no}`, function () {
              let selectedUsers = [];
              let chatNames = [];
              $(`#addUserList_\${chat_no} input[type="checkbox"]:checked`).each(function () {
                  const value = $(this).val();
                  selectedUsers.push(value.split(":")[0]);
                  chatNames.push(value.split(":")[1]);
              });
      
              if (selectedUsers.length > 0) {
                  chat_users_name += ", " + chatNames.join(', ');
                  console.log("addUser chat_users_name:", chat_users_name);
                  $.ajax({
                      url: '<%= request.getContextPath() %>/chat/addUser.do',
                      method: 'POST',
                      contentType: 'application/json; charset=utf-8',
                      data: JSON.stringify({ chat_no: chat_no, chat_users_name: chat_users_name, users: selectedUsers }),
                      success: function (result) {
                          if(result.trim() === "Success") {
                              closeAddUserModal(modalId);
                              chatUser(chat_no);
                              chatName(chat_no);
                          }else {
                              alert("사용자 초대에 실패했습니다.");
                          }
                      },
                      error: function () {
                          alert("서버 오류로 인해 사용자 초대에 실패했습니다.");
                      }
                  });
              } else {
                  alert("초대할 사용자를 선택해주세요.");
              }
          });
      }
      ``` 
  - 해당 경험을 통해 알게 된 점
    - 각 채팅방에 맞게 모달을 동적으로 생성해 사용하니 동시성 문제를 해결할 수 있었고, 유연한 코드 설계의 중요성을 배웠습니다.

2️⃣ 여러창이 있는 경우 웹소켓 연결문제
  - 문제 배경
    - 여러 채팅방을 연 상태에서 다른 채팅방에 입력 후 다시 다른 채팅방에 입력하려는 경우 웹소켓 연결 오류가 발생
  - 해결 방법
    - 해당 채팅방의 웹소켓을 인덱스 기본키로 넣어 배열에 저장
  - 코드 비교
    - 
      - 수정전
      ```
      let socket;      
      function connectWebSocket() {
      ```
      - 수정후
      ```
      const chatWebSockets = {}; // 채팅방 WebSocket 저장 객체
      function connectWebSocket(chat_no) {
          // 채팅방 번호에 따라 WebSocket을 생성
          if(!chatWebSockets[chat_no]) {
      ``` 
  - 해당 경험을 통해 알게 된 점
    - 여러 창에서 웹소켓을 사용할 때는 각 채팅방의 웹소켓을 기본키로 배열에 저장해 관리하면 연결 문제를 효과적으로 해결할 수 있음을 배웠습니다.
   

3️⃣ 채팅방 검색결과 초기화
  - 문제 배경
    - 채팅방 이름과 채팅 참가자 이름으로 해당 채팅방을 검색하면 setInterval이 돌면서 다시 검색전 채팅목록으로 되돌아가는 문제 발생 
  - 해결 방법
    - setInterval을 함수로 만들어 메인채팅방 상태일때의 setInterval과 검색어를 입력했을때의 setInterval을 분리하여 적용
  - 코드 비교
    - 
      - 수정전
      ```
      window.onload = function(){
        setInterval(function(){
          $.ajax({
                url: "<%= request.getContextPath() %>/chat/chat.do",
                type: "GET",
                success: function(data) {
                  let html = ``;
                  for(item of data.list){
                html += `
                <li onclick="chatRoomView(\${item.chat_no},'\${item.chat_users_name}');">
                  <div class="chat_item">
                    <div class="message_wrapper">
                           <div class="chat_name">\${item.chat_users_name}</div>`;
                      if(item.unread_count > 0) {
                        html += `<div class="unread_count">\${item.unread_count}</div>`;
                      }else{
                        html += `<div></div>`;
                      }
                      html += `</div>
                          <div class="last_message_wrapper">
                              <div class="last_message">\${item.chat_message_content || ""}</div>
                              <div class="last_message_time">\${item.chat_message_time || ""}</div>
                          </div>
                      </div>
                </li>`;
              }
                    $("#chatRoomList").html(html);
                }
            });
        },1000);
      }
      ```
      - 수정후
      ```
      window.onload = function(){
        startChatInterval();
      }
      let chatInterval;

      function startChatInterval() {
          chatInterval = setInterval(function(){
              $.ajax({
                  url: "<%= request.getContextPath() %>/chat/chat.do",
                  type: "GET",
                  success: function(data) {
                  	let html = ``;
      	        	for(item of data.list){
      					html += `
      					<li onclick="chatRoomView(\${item.chat_no},'\${item.chat_users_name}');">
      						<div class="chat_item">
      							<div class="message_wrapper">
      				           	 <div class="chat_name">
      					           	\${item.chat_users_name}`;
      		           	if(item.user_count > 2) {   	
      	           		html += `   <span class="user_count">\${item.user_count}</span>`;
      		           	}
      		           	html += `</div>`;
      		            if(item.unread_count > 0) {
      		           		html += `<div class="unread_count">\${item.unread_count}</div>`;
      		            }else{
      		            	html += `<div></div>`;
      		            }
      		            html += `</div>
      		            		<div class="last_message_wrapper">
      				                <div class="last_message">\${item.chat_message_content || ""}</div>
      				                <div class="last_message_time">\${item.chat_message_time || ""}</div>
      				            </div>
      				        </div>
      					</li>`;
      				}
      	            $("#chatRoomList").html(html);
                  }
              });
          }, 1000);
      }
      
      function stopChatInterval() {
          clearInterval(chatInterval);
      }
      
      function startChatSearchInterval(searchValue) {
          chatInterval = setInterval(function(){
              $.ajax({
                  url: "<%= request.getContextPath() %>/chat/chat.do",
                  type: "GET",
                  data: { search_value: searchValue },
                  success: function(data) {
                  	let html = ``;
      	        	for(item of data.list){
      					html += `
      					<li onclick="chatRoomView(\${item.chat_no},'\${item.chat_users_name}');">
      						<div class="chat_item">
      							<div class="message_wrapper">
      				           	 <div class="chat_name">
      					           	\${item.chat_users_name}`;
      		           	if(item.user_count > 2) {   	
      	           		html += `   <span class="user_count">\${item.user_count}</span>`;
      		           	}
      		           	html += `</div>`;
      		            if(item.unread_count > 0) {
      		           		html += `<div class="unread_count">\${item.unread_count}</div>`;
      		            }else{
      		            	html += `<div></div>`;
      		            }
      		            html += `</div>
      		            		<div class="last_message_wrapper">
      				                <div class="last_message">\${item.chat_message_content || ""}</div>
      				                <div class="last_message_time">\${item.chat_message_time || ""}</div>
      				            </div>
      				        </div>
      					</li>`;
      				}
      	            $("#chatRoomList").html(html);
                  }
              });
          }, 1000);
      }

      function chatSearch() {
        const searchValue = $("#search_input").val();
        
        stopChatInterval();
        
        $.ajax({
            url: "<%= request.getContextPath() %>/chat/chat.do",
            type: "GET",
            data: { search_value: searchValue },
            success: function(data) {
                let html = '';
                for(item of data.list) {
                    html += `
                        <li onclick="chatRoomView(\${item.chat_no}, '\${item.chat_users_name}');">
                            <div class="chat_item">
                                <div class="message_wrapper">
                                    <div class="chat_name">
                                        \${item.chat_users_name}`;
                    if (item.user_count > 2) {
                        html += `       <span class="user_count">\${item.user_count}</span>`;
                    }
                    html += `</div>`;
                    if (item.unread_count > 0) {
                        html += `<div id="unread_count_\${item.chat_no}" class="unread_count">\${item.unread_count || ""}</div>`;
                    } else {
                        html += `<div id="unread_count_\${item.chat_no}">\${item.unread_count || ""}</div>`;
                    }
                    html += `</div>
                                <div class="last_message_wrapper">
                                    <div class="last_message">\${item.chat_message_content || ""}</div>
                                    <div class="last_message_time">\${item.chat_message_time || ""}</div>
                                </div>
                            </div>
                        </li>`;
                }
    
                $("#chatRoomList").html(html); // 검색 결과를 업데이트
                startChatSearchInterval(searchValue);
            },
            error: function(err) {
                console.error("검색 중 오류 발생:", err);
            }
        });
    }
      ``` 
  - 해당 경험을 통해 알게 된 점
    - setInterval을 분리해서 메인 채팅방 상태와 검색 상태를 따로 처리한 방식은 코드를 더 이해하기 쉽고 관리하기 좋게 만들어준다는 걸 깨달았습니다


<br>


📝개선할 부분
-
  - 현재 여러 채팅방을 관리하기 위해 웹소켓을 배열에 담아 사용하는 방식을 쓰고 있습니다. 이를 개선하기 위해 토픽을 사용하여 하나의 웹소켓으로 모든 채팅방을 관리하도록 변경해야 합니다
  - 팀원 간의 일정을 최대한 조율하려 했으나, 프로젝트 중간에 팀원 중도 포기와 독감 등으로 인한 결원이 발생하여 작업에 차질이 생겼습니다. 이로 인해 프로젝트 기능 구현이 완벽히 마무리되지 못했습니다. 앞으로는 일정 관리에 더 많은 유연성을 부여하려 합니다.

 
<br>
     
<div align="right">
  
[목차로](#목차)

</div>
