function deleteGood(id) {
	var url = "deleteFind";
	var data = new Object();
	data.id = id;
	$.post(url, data, function(resp, status) {
		dealResponse(resp, status, "删除失败");
	});
}


function findLoster(id) {
	var url = "findLoster";
	var data = new Object();
	data.id = id;
	$.post(url, data, function(resp, status) {
		dealResponse(resp, status, "操作失败");
	});
}