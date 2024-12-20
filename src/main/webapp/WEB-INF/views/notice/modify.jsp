<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
	<section>
		<div class="write-container">
			<div class="write-view">
				<h2 class="write-title">공지글 작성하기</h2>
				<form action="modify.do" method="post">
					<input type="hidden" name="post_no" value="${vo.post_no }">
					<table class="write-table">
						<tr>
							<th>글제목</th>
							<td><input type="text" name="post_title" value="${vo.post_title }"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="post_content" cols="40" rows="10">${vo.post_content }</textarea></td>
						</tr>
					</table>
					<button class="write-action">수정</button>
				</form>
					<button class="write-action" onclick="location.href='<%=request.getContextPath() %>/notice/list.do'">취소</button>
			</div>
		</div>
	</section>
</body>
</html>