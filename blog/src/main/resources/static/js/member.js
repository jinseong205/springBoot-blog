
let index = {
	init: function() {
		$("#btn-save").on("click", () => { // this 바인딩
			this.save();
		});
		$("#btn-update").on("click", () => { // this 바인딩
			this.update();
		});
		/*
		$("#btn-login").on("click", () => { // this 바인딩
			this.login();
		});
		*/
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);

		//ajax 통신을 이용해서 parameter를 json으로 변경하여 request
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",		//"/api/member",
			data: JSON.stringify(data), //http body data
			contentType: "application/json; charset=utf-8", //request body dataType
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 500) {
				alert("회원가입에 실패하였습니다. \n" + res.data);
			}
			else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/auth/loginForm";
			}

		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);

		//ajax 통신을 이용해서 parameter를 json으로 변경하여 request
		$.ajax({
			type: "PUT",
			url: "/member",		//"/api/member",
			data: JSON.stringify(data), //http body data
			contentType: "application/json; charset=utf-8", //request body dataType
			dataType: "json" //response dataType
		}).done(function(res) {

			if (res.status === 500) {
				alert("회원수정에 실패하였습니다. \n" + res.data);
			}
			else {
				alert("회원수정이 완료되었습니다.");
				location.href = "/auth/loginForm";
			}
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},
	/*
	login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		console.log(data);

		//ajax 통신을 이용해서 parameter를 json으로 변경하여 request
		$.ajax({
			type: "POST",
			url: "/api/member/login",
			data: JSON.stringify(data), //http body data
			contentType: "application/json; charset=utf-8", //request body dataType
			dataType: "json" //response dataType
		}).done(function(res) {
			alert("로그인이 완료되었습니다.");
			console.log(res);
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	}
	*/
}

index.init();