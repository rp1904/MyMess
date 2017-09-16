<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.byb.bhojan.util.ProjectConstant" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Reset Password | <%=ProjectConstant.PROJECT_NAME%></title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" href="../resources/dist/img/favicon.png" type="image/gif" sizes="16x16">
 <!-- Bootstrap 3.3.6 -->
 <link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" href="../resources/viewCss/login.css">
</head>
<body>

<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<c:if test="${empty type && status != 'success'}">	
					<div class="panel panel-login">			
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-12 text-center">
									<a href="#" class="active" id="login-form-link">Reset Password</a>
								</div>
							</div>
							<hr>
						</div>
						<c:if test="${empty type && (empty status || status == 'danger')}">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<form:form id="passwordResetForm" method="post" action="resetpassword" modelAttribute="user">
									
											<form:hidden id="userIdPk" name="userIdPk" path="userIdPk"/>
											
											<div class="form-group">
												<form:input id="email" name="email" path="email"  tabindex="1" class="form-control" readonly="true" />
											</div>
											
											<div class="form-group">
												<form:password id="password" name="password" path="password" tabindex="2" class="form-control" placeholder="Password" />
											</div>
											
											<div class="form-group">
												<form:password id="confirmPassword" name="confirmPassword" path="confirmPassword" tabindex="3" class="form-control" placeholder="Confirm Password" />
											</div>
<!-- 											col-xs-10 col-sm-10 col-lg-10 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 -->
											<c:if test="${not empty status}">
												<div class="alert alert-${status}" role="alert">
												<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												  <strong>${message}</strong>
												</div>
											</c:if>
											
											<div class="form-group">
												<div class="row">
													<div class="col-sm-6 col-sm-offset-3">
														<button type="submit" class="form-control btn btn-login">Reset</button>
													</div>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</c:if>
				
				<c:if test="${status == 'success'}">	
					<div class="alert alert-${status}" role="alert">
					  <h2 class="text-center"> ${message}</h2>
					  <h5 class="text-center text-info"> Please login in app with new password.</h5>
					</div>
				</c:if>
				
				<c:if test="${not empty type}">	
					<div class="alert alert-${status}" role="alert">
					  <h2 class="text-center"> ${message}</h2>
					</div>
				</c:if>
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


