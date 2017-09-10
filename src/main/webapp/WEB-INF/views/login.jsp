<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <!-- Bootstrap 3.3.6 -->
 <link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" href="../resources/viewCss/login.css">
</head>
<body>

<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 text-center">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<c:if test="${not empty error}">
								<div class="error">${error}</div>
							</c:if>
							<c:if test="${not empty msg}">
								<div class="msg">${msg}</div>
							</c:if>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
							<form:form id="loginForm" method="post" action="j_spring_security_check" modelAttribute="superAdmin">
							
									<div class="form-group">
										<form:input id="email" name="email" path="email"  tabindex="1" class="form-control" placeholder="Email Or Mobile Number" />
									</div>
									
									<div class="form-group">
										<form:password id="password" name="password" path="password" tabindex="2" class="form-control" placeholder="Password" />
									</div>
									
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
											</div>
										</div>
									</div>
									
<!-- 									<div class="form-group"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12"> -->
<!-- 												<div class="text-center"> -->
<!-- 													<a href="#" tabindex="5" class="forgot-password">Forgot Password?</a> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
									
<!-- 									<div class="form-group"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-lg-12"> -->
<!-- 												<div class="text-center"> -->
<!-- 													New Member? Register <a href="member-registration" tabindex="5" class="forgot-password">Here</a> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
								
								</form:form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- jQuery 2.2.3 -->
<script src="../resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../resources/bootstrap/js/bootstrap.min.js"></script>

<script src="../resources/viewJs/login.js"></script>

</body>
</html>


