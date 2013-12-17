//检测邮箱
function chkEmail(obj) {
	var email = jQuery(obj).val();
	if (email == "" || !isEmail(email)) {
		jQuery("#message").html("邮箱格式不正确!");
		return false;
	}
	jQuery("#message").html("");
	return true;
}

function isEmail(str) {
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(str);
}