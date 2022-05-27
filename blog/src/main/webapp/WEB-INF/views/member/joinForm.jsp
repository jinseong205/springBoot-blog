<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="username">ID</label> <input type="text" class="form-control" placeholder="Enter ID" id="id">
		</div>
		<div class="form-group">
			<label for="pwd">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	
	<button id="btn-save" class="btn btn-primary">로그인</button>
</div>

<script src="/blog/js/member.js"></script>
<%@include file="../layout/footer.jsp"%>


