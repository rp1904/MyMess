<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Admin Registration</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="resources/viewCss/register.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<!-- multistep form -->
			<form:form id="msform" modelAttribute="admin" method="POST" action="mess-registration">
				<!-- progressbar -->
				<ul id="progressbar">
					<li class="active" style="margin-left: 17%;">Account Setup</li>
<!-- 					<li>Social Profiles</li> -->
					<li>Personal Details</li>
				</ul>
				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">Create your account</h2>
<!-- 					<h3 class="fs-subtitle">This is step 1</h3> -->
					
					<form:input path="email" placeholder="Email"/>
					<form:errors path="email" class="control-label" />
					<form:input path="mobileNumber" placeholder="Mobile Number"/>
					<form:errors path="mobileNumber" class="control-label" />
					<form:password path="password" placeholder="Password" /> 
					<form:errors path="password" class="control-label" />
					<form:password path="confirmPassword" placeholder="Confirm Password" /> 
					<form:errors path="confirmPassword" class="control-label" />
					<input type="button" name="next" class="next action-button" value="Next" />
				</fieldset>
<!-- 				<fieldset> -->
<!-- 					<h2 class="fs-title">Social Profiles</h2> -->
<!-- 					<h3 class="fs-subtitle">Your presence on the social network</h3> -->
<!-- 					<input type="text" name="twitter" placeholder="Twitter" /> <input -->
<!-- 						type="text" name="facebook" placeholder="Facebook" /> <input -->
<!-- 						type="text" name="gplus" placeholder="Google Plus" /> <input -->
<!-- 						type="button" name="previous" class="previous action-button" -->
<!-- 						value="Previous" /> <input type="button" name="next" -->
<!-- 						class="next action-button" value="Next" /> -->
<!-- 				</fieldset> -->
				<fieldset>
					<h2 class="fs-title">Mess Details</h2>
<!-- 					<h3 class="fs-subtitle">We will never sell it</h3> -->
					<form:input path="adminInfo.messName" placeholder="Mess Name"/> 
					<form:errors path="adminInfo.messName" class="control-label" />
					<form:input path="adminInfo.ownerName" placeholder="Owner Full Name"/> 
					<form:errors path="adminInfo.ownerName" class="control-label" />
					
					<form:input path="adminInfo.messAddress.postalAddress" placeholder="Address"/> 
					<form:errors path="adminInfo.messAddress.postalAddress" class="control-label" />
					<form:input path="adminInfo.messAddress.city" placeholder="City"/> 
					<form:errors path="adminInfo.messAddress.city" class="control-label" />
					<form:input path="adminInfo.messAddress.state" placeholder="State"/> 
					<form:errors path="adminInfo.messAddress.state" class="control-label" />
					<form:input path="adminInfo.messAddress.PINcode" placeholder="PIN Code"/> 
					<form:errors path="adminInfo.messAddress.PINcode" class="control-label" />
					
					<input type="button" name="previous" class="previous action-button"
						value="Previous" /> <input type="submit" name="submit"
						class="submit action-button" value="Submit" />
				</fieldset>
			</form:form>
		</div>
	</div>

	<!-- jQuery-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

	<script src="resources/viewJs/register.js"></script>

</body>
</html>


