$(function() {
	$("#search").click(function() {
		var searchInfo = $("#search-info").val();
		if (searchInfo.length == 0) {
			showNoticeMSG("请输入搜索信息");
			return;
		}
		search(searchInfo);
	});
	load();
});

function load() {
	//从服务器加载数据
	$("#show-lost-goods").load("lostFound/allGoods", function(res, status, x) {
		if (status == "success") {
			$("#lost-good-table").addClass("table table-hover text-center");
			$("img").addClass("img-thumbnail image-rounded img-responsive show-image");
			//为图片设置鼠标监听
			$("img").mouseenter(function() {
				$(this).removeClass("show-image");
				$(this).addClass("show-bigger-image");
			});
			$("img").mouseleave(function() {
				$(this).removeClass("show-bigger-image");
				$(this).addClass("show-image");
			});
		}
	});
}

function search(info) {
	//从服务器加载数据
	$("#show-lost-goods").load("lostFound/search", {
		searchInfo : info
	}, function(res, status, x) {
		if (status == "success") {
			$("#lost-good-table").addClass("table table-hover text-center");
			$("img").addClass("img-thumbnail image-rounded img-responsive show-image");
			//为图片设置鼠标监听
			$("img").mouseenter(function() {
				$(this).removeClass("show-image");
				$(this).addClass("show-bigger-image");
			});
			$("img").mouseleave(function() {
				$(this).removeClass("show-bigger-image");
				$(this).addClass("show-image");
			});
		}
	});
}