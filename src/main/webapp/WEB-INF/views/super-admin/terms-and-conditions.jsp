<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Terms And Conditions</title>
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
        Terms And Conditions
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Terms And Conditions</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     <div class="row">
     	 <div class="box">
     		 <form:form id="terms-and-conditions-form" class="form-horizontal" method="post" action="#" modelAttribute="adminSettings">
	            <div class="box-body pad">
	                <form:textarea cssClass="textarea" path="termsAndConditions" style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></form:textarea>
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
