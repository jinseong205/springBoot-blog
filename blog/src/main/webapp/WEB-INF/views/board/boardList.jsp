<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../layout/boardSideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../layout/nav.jsp"%>

			<div id="page-content-wrapper">
				<div id="content" class="container-fluid ">
					<br />
					<div class="d-flex flex-column">
						<c:choose>
							<c:when test="${not empty principal}">
								<div class="clearfix container-fluid">
									<div class="float-left">
										<button class="btn btn-primary" id="sidebarToggle"><></button>
									</div>

									<div class="float-left ml-3">
										<h3>
											<c:choose>
												<c:when test="${category eq 0}">
												Free Board
												</c:when>
												<c:when test="${category eq 1}">
												Profile
												</c:when>
												<c:when test="${category eq 2}">
												Portfolio
												</c:when>
												<c:when test="${category eq 3}">
												Toy Project
												</c:when>
											</c:choose>
										</h3>
									</div>
									<div class="float-right">
										<a href="/board/saveForm" class="btn btn-secondary ">글 작성</a>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="clearfix container-fluid">
									<div class="float-left">
										<button class="btn btn-primary" id="sidebarToggle"><></button>
									</div>
									<div class="float-left ml-3">
										<h3>
											<c:choose>
												<c:when test="${category eq 0}">
												Free Board
												</c:when>
												<c:when test="${category eq 1}">
												Profile
												</c:when>
												<c:when test="${category eq 2}">
												Portfolio
												</c:when>
												<c:when test="${category eq 3}">
												Toy Project
												</c:when>
											</c:choose>
										</h3>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
					<br />
					<div class="ml-3 mr-3 mb-5">
						<c:forEach var="board" items='${boards.content}'>
							<div class="card m-2">
								<div class="card-body">
									<a href="/board/boardDetail/${board.id}">
										<div>
											<h4 class="d-flex card-title align-self-start">${board.title}</h4>
											<pre>${board.member.username} | ${fn:substring(board.createDate,0,10)}</pre>
										</div>
									</a>
								</div>
							</div>
						</c:forEach>

						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${boards.first}">
									<li class="page-item disabled"><a class="page-link" href="?category=${category}&page=${boards.number-1}">Previous</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="?category=${category}&page=${boards.number-1}">Previous</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${boards.last}">
									<li class="page-item disabled"><a class="page-link" href="?category=${category}&page=${boards.number+1}">Next</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="?category=${category}&page=${boards.number+1}">Next</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
					<%@include file="../layout/footer.jsp"%>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
