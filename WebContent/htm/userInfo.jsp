<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<title>用户信息</title>
<%@ include file="head.jsp"%>
</head>
<body>

 <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container ">
        <div class="navbar-header">
          <a class="navbar-brand" href="#">USERMODULE</a>
        </div>
        <div class="navbar-collapse collapse">

          <ul class="nav navbar-nav navbar-right">
            <li><a href="javascript:;;" onclick="logout('<%=request.getAttribute("uid")%>')">登出</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	<div class="container mgtop40">
		<div class="row">
			<div class="col-md-8">

				<h3 class="text-center">
					个人信息
					<button type="button" class="btn btn-primary btn-xs"
						onclick="editAttr()">修改个人资料</button>
				</h3>

				<form class="form-horizontal .mgl" id="info">
					<input type="hidden" name="uid" value="<%=request.getAttribute("uid")%>"/>
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="inputEmail3" readonly
								value="<%=request.getAttribute("email")%>">
						</div>
					</div>
					<div class="form-group">
						<label for="inputnickname" class="col-sm-2 control-label">昵称</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="nickname" name="nickname"
								value="<%=request.getAttribute("nickname")%>" readonly>
						</div>
					</div>
					<div class="form-group">
						<label for="inputsex" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-7">
							<label class="checkbox-inline"> <input type="checkbox"
								id="sex" value="1" disabled
								<%if (request.getAttribute("sex").equals("1")) {%> checked <%}%>>
								男
							</label> <label class="checkbox-inline"> <input type="checkbox"
								id="sex" value="0" disabled
								<%if (request.getAttribute("sex").equals("0")) {%> checked <%}%>>
								女
							</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputdesc" class="col-sm-2 control-label">个人简介</label>
						<div class="col-sm-7">
							<textarea id="desc" name="desc" class="form-control" rows="5" readonly><%=request.getAttribute("desc")%></textarea>
						</div>
					</div>
					<div class="form-group" id="udButton" style="display:none;">
				    <div class="col-sm-offset-2 col-sm-8">
				      <button type="submit" class="btn btn-primary" onclick="updateInfo();">更新资料</button>
				      <button type="button" class="btn btn-primary" onclick="cancelUpdate();">取消</button>
				    </div>
				  </div>
				</form>
			</div>
			<div class="col-md-4">
				<img alt="200x200" src="<%=request.getAttribute("userImageUrl")%>"
					class="img-circle" />
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<script type="text/javascript">
		function editAttr(){
			var userForm = jQuery("#info");
			jQuery(userForm).find("input[name='nickname']").removeAttr("readonly");
			jQuery(userForm).find("textarea[name='desc']").removeAttr("readonly");
			jQuery("#udButton").show();
		}
		
		function updateInfo(){
			var userForm = jQuery("#info");
			var uid = jQuery(userForm).find("input[name='uid']").val();
			var nickname = jQuery(userForm).find("input[name='nickname']").val();
			var desc = jQuery(userForm).find("textarea[name='desc']").val();
			jQuery.ajax({
	        	url:"userInfo",
	        	type:"POST",
				data:"uid="+ uid + "&nickname=" + nickname + "&desc=" + desc,
	        	success:function(response){
	        		var data = eval('(' + response + ')');
					alert(data.message);
					location = data.url;
	        	},
	        	error:function(){        		
	        	}
	   		 });
		}
		
		function cancelUpdate(){
			location.reload();
		}
		
		function logout(uid){
			jQuery.ajax({
	        	url:"signin",
	        	type:"GET",
				data:"uid="+ uid,
	        	success:function(response){
	        		var data = eval('(' + response + ')');
					location = data.url;
	        	},
	        	error:function(){        		
	        	}
	   		 });
		}
		

	</script>

</body>
</html>