<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Privay Policy</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Privay Policy
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Privay Policy</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     <div class="row">
     	 <div class="box">
            <form:form id="privacy-policy-form" class="form-horizontal" method="post" action="#" modelAttribute="adminSettings">
	            <div class="box-header">
	              <h3 class="box-title">Bootstrap WYSIHTML5
	                <small>Simple and fast</small>
	              </h3>
	              <!-- tools box -->
	              <div class="pull-right box-tools">
	                <button type="button" class="btn btn-default btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse">
	                  <i class="fa fa-minus"></i></button>
	                <button type="button" class="btn btn-default btn-sm" data-widget="remove" data-toggle="tooltip" title="Remove">
	                  <i class="fa fa-times"></i></button>
	              </div>
	              <!-- /. tools -->
	            </div>
	            <!-- /.box-header -->
	            <div class="box-body pad">
	             
	                <form:textarea cssClass="textarea" path="privacyPolicy" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></form:textarea>
	              
	            </div>
	            <div class="box-footer">
	               <button type="submit" class="btn btn-success">Update</button>
	            </div>
            </form:form>
          </div>
     </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
 <%@include file="../comman-to-all/comman-js-files-links.jsp"%>
 
</div>
<!-- ./wrapper -->
<script>
$(document).ready(function() {
	 $(".textarea").wysihtml5();
});
</script>

</body>
</html>
