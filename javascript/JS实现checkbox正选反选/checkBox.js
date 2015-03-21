/********Attention Please:this piece of code only for jQuery v1.6 or higher*********/
"use strict";
$(function() {
	$("#checkAll").click(function() {
		var checks = $("[name='check']"); //收集所有单选框
		if (!$("#checkAll").prop("checked")) { //如果单选框为未选中
			$.each(checks, function(i, item) {
				$(item).prop("checked", false);
			})
		}else {
			$.each(checks, function(i, item) {
				$(item).prop("checked", true);
			})
		}

		$("[name='check']").click(function() { 
			if ($("[name='check']:checked").length === checks.length) {
				$("#checkAll").prop("checked", true);
			} else {
				$("#checkAll").prop("checked", false);
			}
		})
	})
})