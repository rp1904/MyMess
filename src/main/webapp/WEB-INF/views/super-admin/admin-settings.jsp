<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Admin Settings</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <div class="content-wrapper">
    <section class="content-header">
      <h1>Admin Settings</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Admin Settings</li>
      </ol>
    </section>

    <section class="content">
      <div class="row">
        <div class="col-xs-12">
        
        <c:if test="${not empty error}">
	        <div class="alert alert-danger" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error!</strong> Invalid Mess Id !
			</div>
        </c:if>
          
           <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Edit &nbsp;&nbsp;&nbsp;<i class="fa fa-edit"></i></h3>
            </div>
            <form:form id="admin-settings-form" class="form-horizontal" method="post" action="#" modelAttribute="adminSettings">
              <div class="box-body">
                  
                <div class="form-group">
                  <form:label path="freeTrialDays" class="col-sm-3 control-label">Free Trial Days</form:label>
                  <div class="col-sm-2">
                    <form:input path="freeTrialDays"  tabindex="2" class="form-control"/>
                    <form:errors path="freeTrialDays" cssClass="error"/>
                  </div>
                </div>
                
                <div class="form-group">
                  <form:label path="notifyBeforeDays" class="col-sm-3 control-label">Notify Mess For Payment From Day</form:label>
                  <div class="col-sm-2">
                    <form:input path="notifyBeforeDays"  tabindex="2" class="form-control"/>
                    <form:errors path="notifyBeforeDays" cssClass="error"/>
                  </div>
                </div>

              </div>
              <div class="box-footer">
                <button type="submit" class="btn btn-success">Update</button>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </section>
  </div>
  
 <%@include file="../comman-to-all/comman-js-files-links.jsp"%>
 
</div>

<script>
$(document).ready(function() {
  
});
</script>

</body>
</html>
