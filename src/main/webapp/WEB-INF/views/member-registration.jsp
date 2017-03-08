<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Member Registration</title>
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
			<form:form id="msform" modelAttribute="member" method="POST" action="member-registration">
				<!-- progressbar -->
				<ul id="progressbar">
					<li class="active">Account Setup</li>
					<li>Social Profiles</li>
					<li>Personal Details</li>
				</ul>
				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">Create your account</h2>
					<h3 class="fs-subtitle">This is step 1</h3>
					<input type="text" name="email" placeholder="Email" /> <input
						type="password" name="pass" placeholder="Password" /> <input
						type="password" name="cpass" placeholder="Confirm Password" /> <input
						type="button" name="next" class="next action-button" value="Next" />
				</fieldset>
				<fieldset>
					<h2 class="fs-title">Social Profiles</h2>
					<h3 class="fs-subtitle">Your presence on the social network</h3>
					<input type="text" name="twitter" placeholder="Twitter" /> <input
						type="text" name="facebook" placeholder="Facebook" /> <input
						type="text" name="gplus" placeholder="Google Plus" /> <input
						type="button" name="previous" class="previous action-button"
						value="Previous" /> <input type="button" name="next"
						class="next action-button" value="Next" />
				</fieldset>
				<fieldset>
					<h2 class="fs-title">Personal Details</h2>
					<h3 class="fs-subtitle">We will never sell it</h3>
					<form:input path="userInfo.firstName" placeholder="First Name"/> 
					<input
						type="text" name="lname" placeholder="Last Name" /> <input
						type="text" name="phone" placeholder="Phone" />
					<textarea name="address" placeholder="Address"></textarea>
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


