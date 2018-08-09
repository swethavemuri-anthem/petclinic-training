<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post Login</title>
<style>
    form:input[type="password"],form:input[type="text"]{width:100%;}
    form:input[type="submit"]:hover {background: #cecece;border-radius: 6px;}
    body{font-family: sans-serif;}
    .formHolder{margin-bottom:20px;}
    .buttonHolder{margin-bottom:20px;text-align:center;}
    .footer{position: absolute;bottom: 0;right: 0;left:0;min-height: 120px;background-color: #333; color:#fff;font-size:12px;}
    form:label {display:block;}
    .loginBox {position:absolute;top:165px;left:35%;width:250px;padding:40px;border:1px solid #cecece;}
    .header {height: 95px;left: 0;right: 0;top: 0;background-color: #006bc2;padding-top: .9rem;position: fixed;}
    .header .logo {display: inline-block;width: 150px;margin-left: 10px;margin-top: 0;vertical-align: top;}
    .footer ul a {color:#fff;}
    .footer ul li {display: inline;}
    .footer ul {list-style-type: none;text-align: center;color:#fff;}
    .footer ul li:after {content: " | ";}
    .footer ul li:last-child:after {content: "";}
    form:input:-webkit-autofill {-webkit-box-shadow: 0 0 0px 1000px white inset;}
	:focus{outline: #8FBEE6 solid 2px; }
  </style>
</head>
<body>
	<!-- <div class="header">
		<img class=" logo" src="/ltss/images/anthem-rev.png" alt="Anthem">
	</div> -->
	<div class="loginBox">
		<form:form method="POST" action="/redirectlogin" modelAttribute="selectedcliententity">
			<div class="formHolder">
				<form:label path="client">Client</form:label>
				<form:input path="client" value="${client}" type="text" readonly="true" style ="width: 200px"></form:input>
			</div>	
			<div class="formHolder">
				<form:label path="org">Org</form:label> 
				<form:select path = "org" name="org" id="org" style ="width: 200px">
						<form:option value="">Select Org</form:option>
						<c:forEach var = "org" items="${orgs}">						
							<form:option value="${org.key}">${org.key}</form:option>
						</c:forEach>
				</form:select>
			</div>
			
			<div class="formHolder">
				<form:label path="app">App</form:label> 
				<form:select path= "app" name="app" style ="width: 200px">
					<form:option value="">Select</form:option>
					<form:option value="petzoo">Pet Zoo</form:option>
					<form:option value="littlepugs">Little Pugs</form:option>
					<form:option value="petmd">PetMD</form:option>
				</form:select>
			</div>
			
			<div class="buttonHolder">
				<input type="submit" value="Process" />
			</div>
		
		</form:form>
	</div>
</body>


</html>