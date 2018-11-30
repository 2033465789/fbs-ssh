// 网页加载完成后
var formData = new FormData();
$(function() {
	// 事件监听
	$("[role='item']").mouseenter(function() {
		$(this).addClass("mouse-enter");
	}).mouseleave(function() {
		$(this).removeClass("mouse-enter");
	});
	// 监听是否选择图片
	$("#web-img").change(function() {
		if ($(this).val().length > 0) {
			formData = new FormData() ;
			formData.append('upload', this.files[0]);
			$(this).removeClass("warning");
		} else {
			$(this).addClass("warning");
		}
	});

	$("a[data-operate]").click(function() {
		var operate = $(this).data("operate");
		$("#" + operate).removeClass("hide");
	});
});

// 所有操作的取消
function cancel(op) {
	$("#" + op).addClass("hide");
}

function addWebsite() {
	var name = $("#web-name").val().trim();
	var desc = $("#web-desc").val().trim();
	var url = $("#web-url").val().trim();
	if (name == "" || desc == "" || url == "" || !formData.has('upload')) {
		showNoticeMSG("请将信息填写完整");
		return;
	} else {
		formData.append("webName", name);
		formData.append("webDesc", desc);
		formData.append("aimURL", url);
	}
	$.ajax({
		url : "addWebsite",
		type : "post",
		data : formData,
		contentType : false,
		processData : false,
		mimeType : "multipart/form-data",
		success : function(data) {
			if (data == "success") {
				$("#add").addClass("hide");
			} else {
				showNoticeMSG("添加失败");
			}
		}
	});
}

function deleteWebsite() {
	var name = $("#delete-website-name").val().trim();
	var url = "deleteWebsite?websiteName=" + name;
	$.get(url, function(resp, status) {
		if (status == "success" && resp == "success") {
			$("#delete").addClass("hide");
			location.reload();
		} else {
			showNoticeMSG(resp);
		}
	});
}