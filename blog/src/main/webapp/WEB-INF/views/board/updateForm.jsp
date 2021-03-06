<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>


<body class="">
	<div class="d-flex" id="wrapper">
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>
			<div class="container">
				<div id="page-content-wrapper">
					<br /> <br />
					<div class="form-group">
						Category: <select id="category" class="selectpicker">
							<option value=0>Free Board</option>
							<c:if test="${principal.member.role eq 'ADMIN'}">
								<option value=1>Profile</option>
								<option value=2>PortFolio</option>
							</c:if>
						</select>
					</div>
					<form>
						<div class="form-group">
							<input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
						</div>
						<div class="form-group">
						<textarea id="ckEditor">${board.content}</textarea>
						</div>
						<input type="hidden" id="id" value="${board.id}">
					</form>
					<button id="btn-update" class="btn btn-primary">글수정 완료</button>
				</div>

			</div>
		</div>
	</div>
	<%@include file="../layout/footer.jsp"%>
	<script src="/js/board.js"></script>
</body>
</html>


