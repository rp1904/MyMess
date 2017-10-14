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
            <form id="privacy-policy-form" class="form-horizontal" method="post" action="privacy-policy">
	            <div class="box-body pad">
	                <textarea class="ckTextArea" id="privacyPolicy" name="privacyPolicy"></textarea>
	            </div>
	            <div class="box-footer">
	               <button type="submit" class="btn btn-success">Update</button>
	            </div>
            </form>
            <input type="hidden" id="hiddenPrivacyPolicy" value="${pp}"/>
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
// 	 $(".textarea").wysihtml5();
	CKEDITOR.replace('privacyPolicy');
	var data = $("#hiddenPrivacyPolicy").val();
	CKEDITOR.instances['privacyPolicy'].setData(data);
});
</script>

</body>
</html>
