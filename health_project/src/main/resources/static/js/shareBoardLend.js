function writeView() {
	$.ajax({
		url: "/shareBoard/lendWriteView.do",
		type: "POST",
		dataType: "html",
		success: function(data) {
			$('.innerBox').html(data);
		},
		error: function() {
			alert(1);
		}
	})
}

function contentView(num) {
	$.ajax({
		url: "/shareboard/lendContentView.do",
		type: "POST",
		data: { shareTradeNum: num },
		dataType: "html",
		success: function(data) {
			$('.innerBox').html(data);
		},
		error: function() {
			alert(1);
		}
	})
}