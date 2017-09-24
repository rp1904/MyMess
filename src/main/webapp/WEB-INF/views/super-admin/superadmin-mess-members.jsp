<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Mess | Members</title>
  <%@include file="../comman-to-all/comman-css-files-links.jsp"%>
</head>
<body>
<div class="wrapper">

  <%@include file="superadmin-menu-sidemenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>${messName}<small> <i class='fa fa-chevron-right mlr_10px'></i> Members</small></h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Mess</li>
        <li class="active">Members</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
       
          <div class="box">
            <div class="box-body">
            <input type="hidden" id="messId" value="${messId}">
              <table id="example" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>User Id</th>
                  <th>Sr No</th>
                  <th>Name</th>
                  <th>Contact Number</th>
                  <th>Contact Email</th>
                  <th class="text-center">Action</th>
                </tr>
                </thead>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          
          <form id="get_member_details_form" action="member-details" method="POST">
          		<input type="hidden" id='messId' name="messId"/>
          		<input type="hidden" id='memberId' name="memberId"/>
          </form>
          
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

    var messMemberTable = $('#example').DataTable({
      "processing": true,
      "ajax": "mess-members/list?messId=" + $('#messId').val(),
      "columns": [
                  { "data": "userIdPk", "visible": false },
                  { "data": null, "orderable": false, "width": "10%"},
                  { "data": "userProfile.fullName" },
                  { "data": "mobileNumber" },
                  { "data": "email" },
                  { "data": null, "orderable": false, "width": "10%"}
              ],
       "columnDefs": [ {
           "targets": -1,
           "data": null,
           "className": 'text-center',
           "defaultContent": "<i id='btn-1' data-toggle='tooltip' title='Details' role='button' class='fa fa-info-circle gi-1_3x'></i>"
       } ]
    });
    
    messMemberTable.on( 'order.dt search.dt', function () {
    	messMemberTable.column(1, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        });
    }).draw();
    
    $('#example tbody').on( 'click', 'i', function () {
        var data = messMemberTable.row( $(this).parents('tr') ).data();
        
		if(this.id == 'btn-1') {
// 			window.location = 'member-details?memberId=' + data.userIdPk + '&messId=' + $('#messId').val();
			$('#memberId').val(data.userIdPk);
			$('#messId').val($('#messId').val());
			$('#get_member_details_form').submit();
		}        
    });
  
  });
</script>

</body>
</html>
