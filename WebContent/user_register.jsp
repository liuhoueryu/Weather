<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

	//检查用户名---字段级
	function checkUsername(){
		
		var username = document.getElementById("username");
		var usernameResult = document.getElementById("usernameResult");
		
		if(username.value.length<5 || username.value.length>10){
			usernameResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>用户名的长度必须在5-10个字符之间</b></font>";
			return false;
		}else{
			usernameResult.innerHTML = "<img src='image/yes.gif'/><font color='green'><b>用户名合法</b></font>";
			return true;
		}
		
	}
	
	//检查密码---字段级
	function checkPassword(){
		
		var password = document.getElementById("password");
		var passwordResult = document.getElementById("passwordResult");
		
		//if(password.value.length==0){
		if(password.value==""){
			passwordResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>密码不能为空</b></font>";
			return false;
		}else{
			passwordResult.innerHTML = "";
			return true;
		}
		
	}
		
	//检查确认密码---字段级
	function checkPassword2(){
		
		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");
		var passwordResult2 = document.getElementById("passwordResult2");
		
		if(password.value!=password2.value){
			passwordResult2.innerHTML = "<img src='image/no.gif'/><font color='red'><b>两次密码必须一致</b></font>";
			return false;
		}else{
			passwordResult2.innerHTML = "";
			return true;
		}
		
	}	
	
	//表单级校验
	function checkData(){
	
		return checkUsername() && checkPassword() && checkPassword2();
		
	}
</script>
</head>

<body>

<h2 align="center">新用户注册</h2>

<form action="UserRegisterServlet" method="post" onsubmit="return checkData()">

	<table align="center">
	
		<tr>
			<td>用户名</td>
			<td width="500px">
				<input type="text" name="username" id="username" onblur="checkUsername()"/>
				<span id="usernameResult"></span>
			</td>
		</tr>
		
		<tr>
			<td>密码</td>
			<td>
				<input type="password" name="password" id="password" onblur="checkPassword()"/>
				<span id="passwordResult"></span>
			</td>
		</tr>
		
		<tr>
			<td>确认密码</td>
			<td>
				<input type="password" id="password2" onblur="checkPassword2()"/>
				<span id="passwordResult2"></span>
			</td>
		</tr>
		
	    <tr>     	
		   <td>性别</td>
		   <td>
		   	<input type="radio" name="gender" value="男" checked />男
		   	<input type="radio" name="gender" value="女" />女
		   </td>
		 </tr>		 
		 
		 <tr>     	
		   <td>职业</td>
		   <td>
		   	  <select name="job">
		   		<option value="程序员" selected>程序员</option>
		   		<option value="美工">美工</option>
		   		<option value="项目经理">项目经理</option>
		   	  </select>
		   </td>
		 </tr>		
		  	 	
		 <tr>     	
		   <td>兴趣爱好</td>
		   <td>
		   		<input type="checkbox" name="interest" value="唱歌" />唱歌
		   		<input type="checkbox" name="interest" value="跳舞" />跳舞
		   		<input type="checkbox" name="interest" value="跑步" />跑步
		   		<input type="checkbox" name="interest" value="游泳" />游泳
		   </td>
		 </tr>	
		
		<tr>
			<td>头像</td>
			<td>
				<input type="radio" name="photo" value="1.gif" checked/> <img src="image/photo/1.gif"/> 
				<input type="radio" name="photo" value="2.gif" /> <img src="image/photo/2.gif"/> 
				<input type="radio" name="photo" value="3.gif" /> <img src="image/photo/3.gif"/> 
				<input type="radio" name="photo" value="4.gif" /> <img src="image/photo/4.gif"/> 
				<input type="radio" name="photo" value="5.gif" /> <img src="image/photo/5.gif"/> 
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="立即注册" />
				<input type="reset" value="重新填写" />
			</td>
		</tr>
		
	</table>	

</form>

</body>

</html>
