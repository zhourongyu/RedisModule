<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>用户注册</title>
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<h2 class="form-regist-heading">
			<img src="http://img.duobei.com/duobei-logo-184-87-20120723.gif"
				width="184" height="87">
		</h2>
		<form class="form-horizontal" class="form-horizontal" id="userRegist" action="UserRegist" method="POST">
			<div class="form-group">
				<label id="message" class="text-danger" for="inputError"></label> 
				<label for="inputEmail" class="col-sm-1 control-label" >Email</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="email" name="email" onblur="chkEmail(this)">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-sm-1 control-label">密码</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password" name="password" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputnickname" class="col-sm-1 control-label">昵称</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="nickname"
						name="nickname" value="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputsex" class="col-sm-1 control-label">性别</label>
				<div class="col-sm-4">
					<label class="checkbox-inline"> <input type="radio"
						name="sex" value="1" checked> 男
					</label> <label class="checkbox-inline"> <input type="radio"
						name="sex" value="0"> 女
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputdesc" class="col-sm-1 control-label">个人简介</label>
				<div class="col-sm-4">
					<textarea id="description" name="description" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="form-group" id="udButton">
				<div class="col-sm-offset-1 col-sm-6">
					<button type="button" class="btn btn-primary" onclick="regist()">注册</button>
					<button type="reset" class="btn btn-primary">取消</button>
				</div>
			</div>
		</form>

	</div>
	<!-- /container -->
	<%@ include file="footer.jsp"%>
	<script>
		function regist(){
			var verify = chkEmail("#email");
			if(verify){
				jQuery("#userRegist").submit();
			}else{
				jQuery("#email").val('');
				jQuery("#email").focus();
			}
		}
	</script>
</body>
</html>