$(function() {
	$('#userId').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}
	});
	$('#pwd').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}

	});
	$('#rpwd').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}
	});
});

function register()
{
	var userId = document.getElementById("userId").value;
	var pwd = document.getElementById("pwd").value;
	var rpwd = document.getElementById("rpwd").value;
	if (pwd != rpwd)
	{
		$.growl.warning({
			title : "",
			message : "两次密码不一致"
		});
		return;
	}
	if (pwd.length < 6)
	{
		$.growl.warning({
			title : "",
			message : "密码至少6位"
		});
		return;
	}

	$.post("userOperate/signup", {
		userName : userId,
		password : pwd
	}, function(data, textStatus, req) {
		if (textStatus == "success")
		{
			if (data == "success")
			{
				location.href = "main.jsp";
			} else
			{
				showNoticeMSG("注册失败");
			}
		} else
		{
			showWarningMSG("无法连接服务器");
		}
	})
}