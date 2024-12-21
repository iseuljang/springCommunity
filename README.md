:speech_balloon: 커뮤니티
-


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
  + 팀원 장이슬
    + 기획 : HTML, CSS 제작, 시퀀스(로그인), 프로젝트 주제제안서, 일정관리(노션), ERD
    + 개발 : 스프링초기설정, 로그인&로그아웃(스프링시큐리티), 채팅방 기능(웹소켓), 관리자 기능
  + 팀원 박인재
    + 기획 : HTML, CSS 제작, 요구사항분석서&프로젝트설계서 작성, PPT
    + 개발 : 부서업무 관리, 주간근무시간 관리, 출퇴근 관리(지오로케이션)
  + 팀장 송지은
    + 기획 : HTML, CSS 제작, 프로토타입, 화면설계서
    + 개발 : 비밀번호 재설정, 내정보조회 및 수정, 근태 관리, 복지 관리
  + 팀원 이동윤(중도포기 12월 18일)
    + 기획 : HTML, CSS 제작, 기획
    + 개발 : 공지게시판 CRUD 
  

<br>

🛠개발환경
-
  + JDK 1.8, MySQL 8.0, TOMCAT 9.0, SPRING FRAMEWORK 4.3.3.RELEASE, SPRING SECURITY 3.2.10.RELEASE, MyBatis 3.4.1
  + JAVA8, HTML5, CSS3, JSP4, JavaScript, jQuery, Ajax
  + SpringToolSuite 3, Visual Studio Code, ERMaster, StarUML, MySQL (Workbench 8.0),GitHub, Notion

<br>


<br>

🖥담당한 기능
-
  - **ERD**
  ![image](https://github.com/user-attachments/assets/60eedb76-2fca-4855-940d-1f5528a52664)

  - **스프링 초기설정**
    - 스프링 시큐리티, 인코딩, jackson, WebSocket, 자바버전업, 스프링 버전업
  - **로그인&로그아웃**
    - 스프링 시큐리티를 사용하여 로그인처리, 로그아웃처리
  - **관리자 직원등록**
    - 단건인 경우 직접 입력하여 직원을 등록
    - &nbsp;
    - 다건인 경우 지정된 엑셀 양식을 받아 입력하여 업로드시켜 직원등록
    - &nbsp;
  - **등록된 직원의 이메일로 직원 아이디와 초기비밀번호를 발송**
    - 직원이 직접 회원가입하는 기능이 없으며 아이디의 경우 "jj"에 사번을 결합하여 아이디를 생성하고 초기비밀번호는 알파벳소문자와 숫자의 랜덤결합으로 6자리 생성
    - &nbsp;
  - **채팅방**
    - &nbsp;



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
      ```
      - 수정후
      ```
      ``` 
  - 해당 경험을 통해 알게 된 점
    - &nbsp;

2️⃣ 여러창이 있는 경우 웹소켓 연결문제
  - 문제 배경
    - 여러 채팅방을 연 상태에서 다른 채팅방에 입력 후 다시 다른 채팅방에 입력하려는 경우 웹소켓 연결 오류가 발생
  - 해결 방법
    - 해당 채팅방의 웹소켓을 인덱스 기본키로 넣어 배열에 저장
  - 코드 비교
    - 
      - 수정전
      ```
      ```
      - 수정후
      ```
      ``` 
  - 해당 경험을 통해 알게 된 점
    - &nbsp;
   

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
    - &nbsp;


<br>


📝개선할 부분
-
  - 
  - 
  - 
  - 
 
<br>
     
<div align="right">
  
[목차로](#목차)

</div>
