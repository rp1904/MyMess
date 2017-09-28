<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Admin Settings</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Admin Settings</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Admin Settings</li>
      </ol>
    </section>

    <!-- Main content -->
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
            <!-- /.box-header -->
            <!-- form start -->
            <form:form id="admin-settings-form" class="form-horizontal" method="post" action="#" modelAttribute="adminSettings">
              <div class="box-body">
                <div class="form-group">
                  <form:hidden path="adminSettingId"/>
                  <form:label path="defaultPayableAmount" class="col-sm-3 control-label">Default Payable Amount</form:label>
                  <div class="col-sm-2">
                    <div class="input-group">
                       <form:input id="defaultPayableAmount" name="defaultPayableAmount" path="defaultPayableAmount"  tabindex="1" class="form-control"/>
                        <div class="input-group-addon">
                           <i class="fa fa-rupee"></i>
                        </div>
                    </div>
                    <form:errors path="defaultPayableAmount" cssClass="error"/>
                  </div>
                  
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" class="btn btn-success">Update</button>
              </div>
              <!-- /.box-footer -->
            </form:form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
 <%@include file="../comman-to-all/comman-js-files-links.jsp"%>
 
</div>
<!-- ./wrapper -->

<script>
$(document).ready(function() {
  
});
</script>

</body>
</html>
