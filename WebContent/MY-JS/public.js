function MSG(title, message) {
	this.title = title;
	this.message = message;
}
function showNoticeMSG(message) {
	var msg = new MSG("", message);
	$.growl.notice(msg);
}

function showWarningMSG(message) {
	var msg = new MSG("", message);
	$.growl.warning(msg);
}

function showErrorMSG(message) {
	var msg = new MSG("", message);
	$.growl.error(message);
}

var basepath = "";

function logOut(path) {
	$.ajax({
		url : path + "/userOperate/logout",
		type : "post",
		success : function(resp, status) {
			dealResponse(resp, status, "注销失败");
		}
	});
}

function show_login(path) {
	basepath = path;
	// 创建悬浮组件
	var login = $("<div id='log-in' class='full-width'></div>");
	var bg = $("<div class='login-overplay'></div>");
	var content = $("<div class='login-content col-md-offset-3 col-md-6 col-sm-12 col-xs-11'></div>");
	// 加载登录页面
	content.load(basepath + "/pageModules/login.html");
	// 组件拼接
	login.append(bg);
	login.append(content);
	// 送页面显示
	$(document.body).append(login);
	bg.click(function() {
		$("#log-in").fadeOut("slow", function() {
			$(this).remove()
		});
	});
}

function LogIn() {
	var pwd = $("#pwd").val();
	var userId = $("#userId").val();
	if (userId == null || userId.length == 0) {
		$("#userId").addClass("warning");
		return;
	}
	if (pwd == null || pwd.length == 0) {
		$("#pwd").addClass("warning");
		return;
	}
	$.ajax({
		url : basepath + "/userOperate/login",
		type : "post",
		data : {
			"userName" : userId,
			"password" : pwd
		},
		success : function(resp) {
			if (resp == "success") {
				location.reload();
			} else if (resp == "error") {
				showWarningMSG("账号或者密码错误");
			} else {
				showWarningMSG("登录失败");
			}
		}
	});
}

function signup() {
	location.href = basepath + "/signup.html";
}

function dealResponse(resp, status, otherInfo) {
	if (resp == "success" && status == "success") {
		location.reload();
	} else if (resp != "") {
		showNoticeMSG(resp);
	} else {
		showNoticeMSG(otherInfo);
	}
}