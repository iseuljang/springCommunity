:speech_balloon: 커뮤니티
-
기업 내 근무 관리와 커뮤니케이션을 효율적으로 지원하는 사내 시스템 개발을 목표로 합니다.

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
    + 개발 : 로그인&로그아웃(스프링시큐리티), 채팅방 기능(웹소켓), 관리자 기능, 게시판CRUD, 댓글CRUD,스프링 필터(XXS 및 비속어처리), 스프링 스케쥴러(회원등록 후 아이디 및 초기비밀번호 이메일 발송)
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
  + SpringToolSuite 4, Visual Studio Code, ERMaster, StarUML, MySQL (Workbench 8.0),GitHub, Notion

<br>


<br>

🖥담당한 기능
-
  - **ERD**
  ![image](https://github.com/user-attachments/assets/60eedb76-2fca-4855-940d-1f5528a52664)

  - **로그인&로그아웃**
    - 스프링 시큐리티를 사용하여 로그인처리, 로그아웃처리
  - **관리자 기능**
    - 직원등록
      - 단건인 경우 직접 입력하여 직원을 등록
      - 다건인 경우 지정된 엑셀 양식을 받아 입력하여 업로드시켜 직원등록
      - 등록된 직원의 이메일로 직원 아이디와 초기비밀번호를 발송
        - 직원이 직접 회원가입하는 기능이 없으며 아이디의 경우 "jj"에 사번을 결합하여 아이디를 생성하고 초기비밀번호는 알파벳소문자와 숫자의 랜덤결합으로 6자리 생성
    - 직원목록 조회 및 정보수정
      - 등록되어있는 직원 목록을 조회
        - 조회 조건은 직원 이름, 재직상태, 부서, 직책, 입사일
      - 직원의 재직상태와 부서, 직책을 수정할 수 있음
    - <img src="https://github.com/iseuljang/springCommunity/blob/main/screen/%EA%B4%80%EB%A6%AC%EC%9E%90.gif">
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
      - 필터를 사용하여 XXS 방지 및 비속어 처리(웹소켓을 사용하여 입력하기 때문에 처음 입력시는 반영되지만 이후 필터동작하여 처리됨)
    - 채팅방 상단고정
      - 즐겨찾는 채팅방등을 상단고정 처리하여 항상 맨위에 조회되게 할 수 있음
    - 채팅방 목록
      - 채팅 조회시 해당 채팅방에서 마지막으로 입력된 메시지와 시간이 표시
      - 안읽은 메시지가 있는 경우 안읽은 메시지 갯수 표시
      - 인터벌을 사용하여 최근 채팅 입력된 채팅방이 목록의 상단으로 올라옴
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
    - <img src="https://github.com/iseuljang/springCommunity/blob/main/screen/%EC%B1%84%ED%8C%85.gif">



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
