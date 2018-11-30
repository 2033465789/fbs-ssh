function deleteShared(fid) {
	var url = "deleteShared";
	$.post(url, {
		id : fid
	}, function(resp, status) {
		dealResponse(resp, status, "删除失败");
	});
}